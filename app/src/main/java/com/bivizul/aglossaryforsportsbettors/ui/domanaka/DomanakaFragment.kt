package com.bivizul.aglossaryforsportsbettors.ui.domanaka

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.aglossaryforsportsbettors.R
import com.bivizul.aglossaryforsportsbettors.appComponent
import com.bivizul.aglossaryforsportsbettors.data.Capiconst.APP_PREFERENCES
import com.bivizul.aglossaryforsportsbettors.data.Capiconst.KEY_CAPEL
import com.bivizul.aglossaryforsportsbettors.data.Content
import com.bivizul.aglossaryforsportsbettors.data.model.SetCapel
import com.bivizul.aglossaryforsportsbettors.databinding.FragmentDomanakaBinding
import com.bivizul.aglossaryforsportsbettors.util.getCapid
import com.bivizul.aglossaryforsportsbettors.util.getCaploc
import com.bivizul.aglossaryforsportsbettors.util.getDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class DomanakaFragment : Fragment(R.layout.fragment_domanaka) {

    @Inject
    lateinit var factory: DomanakaViewModelFactory.Factory

    private val binding by viewBinding(FragmentDomanakaBinding::bind)
    private val preferences by lazy {
        requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }
    private val viewModel by viewModels<DomanakaViewModel> {
        factory.create(SetCapel(getCaploc(requireContext()), getCapid(preferences)))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getCapel.collect { content ->
                    when (content) {
                        is Content.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Content.Success -> {
                            content.data?.let { getCapel ->
                                if (getCapel.getCapel == "no") {
                                    delay(1000)
                                    binding.progressBar.visibility = View.GONE
                                    findNavController().navigate(R.id.action_domanakaFragment_to_mainFragment)
                                } else {
                                    delay(1000)
                                    binding.progressBar.visibility = View.GONE
                                    findNavController().navigate(
                                        R.id.action_domanakaFragment_to_capelFragment,
                                        bundleOf(KEY_CAPEL to getCapel.getCapel)
                                    )
                                }
                            }
                        }
                        is Content.Error -> {
                            binding.progressBar.visibility = View.GONE
                            getDialog(requireContext(), requireActivity())
                        }
                    }
                }
            }
        }
    }
}
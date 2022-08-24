package com.bivizul.aglossaryforsportsbettors.ui.glossary

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.aglossaryforsportsbettors.R
import com.bivizul.aglossaryforsportsbettors.appComponent
import com.bivizul.aglossaryforsportsbettors.data.Content
import com.bivizul.aglossaryforsportsbettors.databinding.FragmentGlossaryBinding
import com.bivizul.aglossaryforsportsbettors.util.getDialog
import kotlinx.coroutines.launch
import javax.inject.Inject

class GlossaryFragment : Fragment(R.layout.fragment_glossary) {

    @Inject
    lateinit var factory: GlossaryViewModelFactory.Factory

    private val binding by viewBinding(FragmentGlossaryBinding::bind)
    private val viewModel by viewModels<GlossaryViewModel> { factory.create() }
    private val glossaryAdapter by lazy { GlossaryAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fastScrollerRecycler.adapter = glossaryAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.glossary.collect { content ->
                    when (content) {
                        is Content.Loading -> {
                            binding.progressBarGlossary.visibility = View.VISIBLE
                        }
                        is Content.Success -> {
                            binding.progressBarGlossary.visibility = View.GONE
                            content.data?.let {
                                glossaryAdapter.submitList(it.glossary)
                            }
                        }
                        is Content.Error -> {
                            binding.progressBarGlossary.visibility = View.GONE
                            getDialog(requireContext(), requireActivity())
                        }
                    }
                }
            }
        }
    }
}
package com.bivizul.aglossaryforsportsbettors.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.aglossaryforsportsbettors.R
import com.bivizul.aglossaryforsportsbettors.databinding.FragmentMainBinding

@Suppress("DEPRECATION")
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private var handlerAnimation = Handler()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPulse()

        binding.buttonGlossary.setOnClickListener {
            stopPulse()
            findNavController().navigate(R.id.action_mainFragment_to_glossaryFragment)
        }
    }

    private fun startPulse() {
        runnable.run()
    }

    private fun stopPulse() {
        handlerAnimation.removeCallbacks(runnable)
    }

    private var runnable = object : Runnable {
        override fun run() {
            with(binding) {
                imgAnimation1.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(1000)
                    .withEndAction {
                        imgAnimation1.scaleX = 1f
                        imgAnimation1.scaleY = 1f
                        imgAnimation1.alpha = 1f
                    }

                imgAnimation2.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(700)
                    .withEndAction {
                        imgAnimation2.scaleX = 1f
                        imgAnimation2.scaleY = 1f
                        imgAnimation2.alpha = 1f
                    }
            }
            handlerAnimation.postDelayed(this, 1500)
        }
    }
}
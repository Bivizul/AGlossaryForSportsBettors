package com.bivizul.aglossaryforsportsbettors.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.aglossaryforsportsbettors.R
import com.bivizul.aglossaryforsportsbettors.data.Capiconst.BACK_ACTIVITY_L
import com.bivizul.aglossaryforsportsbettors.data.Capiconst.BACK_ACTIVITY_P
import com.bivizul.aglossaryforsportsbettors.databinding.ActivityMainBinding
import com.bivizul.aglossaryforsportsbettors.util.checkCheck
import com.bivizul.aglossaryforsportsbettors.util.getDialog

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (checkCheck(this)) {
            setContentView(R.layout.activity_main)
            val orientation = resources.configuration.orientation
            val image = when (orientation) {
                Configuration.ORIENTATION_PORTRAIT -> BACK_ACTIVITY_P
                Configuration.ORIENTATION_LANDSCAPE -> BACK_ACTIVITY_L
                else -> BACK_ACTIVITY_L
            }
            binding.backActivity.load(image)
        } else {
            getDialog(this, this)
        }
    }
}
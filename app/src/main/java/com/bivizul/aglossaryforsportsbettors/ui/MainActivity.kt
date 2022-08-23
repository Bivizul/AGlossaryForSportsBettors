package com.bivizul.aglossaryforsportsbettors.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bivizul.aglossaryforsportsbettors.R
import com.bivizul.aglossaryforsportsbettors.util.checkCheck
import com.bivizul.aglossaryforsportsbettors.util.getDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (checkCheck(this)) {
            setContentView(R.layout.activity_main)
        } else {
            getDialog(this, this)
        }
    }
}
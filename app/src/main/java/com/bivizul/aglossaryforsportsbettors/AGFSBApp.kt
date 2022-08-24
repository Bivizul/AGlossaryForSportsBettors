package com.bivizul.aglossaryforsportsbettors

import android.app.Application
import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.bivizul.aglossaryforsportsbettors.data.Capiconst.AF_DEV_KEY
import com.bivizul.aglossaryforsportsbettors.di.AppComponent
import com.bivizul.aglossaryforsportsbettors.di.DaggerAppComponent

class AGFSBApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

        // TODO(OneSignal)
//        // Enable verbose OneSignal logging to debug issues if needed.
//        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
//
//        // OneSignal Initialization
//        OneSignal.initWithContext(this)
//        OneSignal.setAppId(ONESIGNAL_APP_ID)

        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, this)
        AppsFlyerLib.getInstance().start(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is AGFSBApp -> appComponent
        else -> applicationContext.appComponent
    }
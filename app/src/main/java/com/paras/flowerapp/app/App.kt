package com.paras.flowerapp.app

import android.app.Application
import com.paras.flowerapp.utils.PrefUtils

class App: Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val sharedPrefUtils by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        PrefUtils(this)
    }
}
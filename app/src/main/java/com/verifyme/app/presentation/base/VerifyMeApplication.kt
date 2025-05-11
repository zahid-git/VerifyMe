package com.verifyme.app.presentation.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class VerifyMeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }


}
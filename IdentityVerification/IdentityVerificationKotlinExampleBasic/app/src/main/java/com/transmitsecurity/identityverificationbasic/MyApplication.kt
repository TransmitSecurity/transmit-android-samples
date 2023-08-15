package com.transmitsecurity.identityverificationbasic

import android.app.Application
import com.transmit.identityverification.TSIdentityVerification

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        TSIdentityVerification.initialize(this,Constants.CLIENT_ID)
    }
}
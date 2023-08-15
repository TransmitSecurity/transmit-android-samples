package com.transmitsecurity.idvsdkdemoapp
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.transmit.identityverification.TSIdentityVerification


open class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        TSIdentityVerification.initialize(this,Constants.CLIENT_ID)
    }
}
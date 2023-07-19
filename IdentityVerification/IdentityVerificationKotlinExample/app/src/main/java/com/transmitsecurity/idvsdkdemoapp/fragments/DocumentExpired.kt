package com.transmitsecurity.idvsdkdemoapp.fragments

import com.transmitsecurity.idvsdkdemoapp.R

class DocumentExpired: SecondFragment() {
    override val recaptureReason: String
        get() = getString(R.string.error_2)

}
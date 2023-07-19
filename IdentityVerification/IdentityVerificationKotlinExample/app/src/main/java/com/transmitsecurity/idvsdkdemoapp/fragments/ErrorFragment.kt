package com.transmitsecurity.idvsdkdemoapp.fragments

import android.graphics.Typeface
import com.transmitsecurity.idvsdkdemoapp.R

class ErrorFragment: FirstFragment() {
    override val title: String
        get() = getString(R.string.error_title)
    override val visibleBtn: Boolean
        get() = false
    override val subtitle: String
        get() = getString(R.string.error_sub_title)
    override val btnTxt: String
        get() = ""
    override val titleWeight: Typeface
        get() = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    override val subTitleWeight: Typeface
        get() = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    override val drawing: Int
        get() = R.drawable.something_wrong
    override val visibleDrawing: Boolean
        get() = true
    override val visibleProgressBar: Boolean
        get() = false

    override fun myClickListener() {
    }
}
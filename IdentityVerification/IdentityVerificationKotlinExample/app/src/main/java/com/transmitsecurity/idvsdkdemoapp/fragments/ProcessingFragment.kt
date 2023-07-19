package com.transmitsecurity.idvsdkdemoapp.fragments

import android.graphics.Typeface
import com.transmitsecurity.idvsdkdemoapp.R


class ProcessingFragment() : FirstFragment() {
    override val title: String
        get() = getString(R.string.processing_title)
    override val visibleBtn: Boolean
        get() = false
    override val subtitle: String
        get() = getString(R.string.processing_sub_title)
    override val btnTxt: String
        get() =""
    override val titleWeight: Typeface
        get() = Typeface.create(Typeface.DEFAULT,Typeface.BOLD)
    override val subTitleWeight: Typeface
        get() = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    override val visibleDrawing: Boolean
        get() = false
    override val drawing: Int
        get() = R.drawable.proccess_competion
    override val visibleProgressBar: Boolean
        get() = true

    override fun myClickListener() {
    }
}
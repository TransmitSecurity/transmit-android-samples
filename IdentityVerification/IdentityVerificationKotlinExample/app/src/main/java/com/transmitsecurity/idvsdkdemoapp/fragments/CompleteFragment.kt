package com.transmitsecurity.idvsdkdemoapp.fragments

import android.graphics.Typeface
import com.transmitsecurity.idvsdkdemoapp.R


class CompleteFragment: FirstFragment() {
    override val title: String
        get() = getString(R.string.completion_title)
    override val visibleBtn: Boolean
        get() = true
    override val visibleDrawing: Boolean
        get() = true
    override val subtitle: String
        get() = ""
    override val btnTxt: String
        get() = getString(R.string.new_btn)
    override val titleWeight: Typeface
        get() = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    override val subTitleWeight: Typeface
        get() = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    override val drawing: Int
        get() = R.drawable.proccess_competion
    override val visibleProgressBar: Boolean
        get() = false

    override fun myClickListener() {
        val newVerification = PreparationFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, newVerification)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    }


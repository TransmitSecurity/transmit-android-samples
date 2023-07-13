package com.transmitsecurity.idvsdkdemoapp.fragments

import android.graphics.Typeface
import androidx.core.app.ActivityCompat
import com.transmitsecurity.idvsdkdemoapp.R


class RequiredCameraPermissionsFragment: FirstFragment() {
    private val CAMERACODE = 100;
    override val title: String
        get() = getString(R.string.camera_permissions_title)
    override val visibleBtn: Boolean
        get() = true
    override val subtitle: String
        get() = getString(R.string.camera_permissions_sub_title)
    override val btnTxt: String
        get() = getString(R.string.camera_permissions_btn)
    override val titleWeight: Typeface
        get() = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    override val subTitleWeight: Typeface
        get() = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    override val drawing: Int
        get() = R.drawable.camera_permissions
    override val visibleDrawing: Boolean
        get() = true
    override val visibleProgressBar: Boolean
        get() = false


    override fun myClickListener() {
        ActivityCompat.requestPermissions(  requireActivity(),arrayOf(android.Manifest.permission.CAMERA), CAMERACODE)
    }

}

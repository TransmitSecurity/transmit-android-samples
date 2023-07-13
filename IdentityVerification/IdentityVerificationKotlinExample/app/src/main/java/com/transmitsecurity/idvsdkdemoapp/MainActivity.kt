package com.transmitsecurity.idvsdkdemoapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.transmit.identityverification.ITSIdentityVerificationStatus
import com.transmit.identityverification.TSIdentityVerification
import com.transmit.identityverification.TSIdentityVerificationError
import com.transmit.identityverification.TSRecaptureReason
import com.transmitsecurity.idvsdkdemoapp.fragments.*
import com.transmitsecurity.idvsdkdemoapp.viewmodel.IdvViewModel



class MainActivity : AppCompatActivity(), ITSIdentityVerificationStatus,
    PreparationFragmentActions {
    private  var accessToken :String?=  null
    private lateinit var startToken :String
    private val viewModel: IdvViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manageFragments(PreparationFragment())


        viewModel.accessToken.observe(this
        ) { newAccessToken ->
            accessToken=newAccessToken
            viewModel.createSessionId("https://api.transmitsecurity.io/")

        }

        viewModel.startToken.observe(this
        ) { newStartToken ->
            startToken=newStartToken
            isCameraPermissionsGranted()

        }

    }

    override fun onStart() {
        super.onStart()
        TSIdentityVerification.registerForStatus(this)
    }


    private fun isCameraPermissionsGranted() {
        // Check if the camera permission is granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Camera permission granted, start Identity Verification here
            TSIdentityVerification.start(this, startToken)
        } else {
            // Request camera permission
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 101)
        }
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission granted, start Identity Verification here
                TSIdentityVerification.start(this,  startToken)
            } else {
                // Camera permission denied, handle unauthorized state
                Log.d("Camera permissions","Camera permissions denied")
            }
        }
    }

    override fun verificationStartCapturing() {
        Log.d("verificationDidReceiveStatus", "verificationCapturing")
    }

    @SuppressLint("WrongViewCast")
    override fun verificationStartProcessing() {
        Log.d("verificationDidReceiveStatus", "verificationStartProcessing")
        manageFragments(ProcessingFragment())

    }

    override fun verificationCompleted() {
        Log.d("verificationDidReceiveStatus", "verificationCompleted")
        manageFragments(CompleteFragment())
    }

    override fun verificationFail(error: TSIdentityVerificationError) {
        Log.d("verificationDidFail", error.name)
        when(error.name){
           "CameraPermissionRequired" ->manageFragments(RequiredCameraPermissionsFragment())
            "SdkDisabled" ->manageFragments(ErrorFragment())
            "SessionNotValid" ->manageFragments(ErrorFragment())
            "VerificationStatusError" ->manageFragments(ErrorFragment())

        }
    }


    override fun verificationRequiresRecapture(reason: TSRecaptureReason?) {
        TSIdentityVerification.recapture(this)
        if (reason != null) {
            Log.d("verificationDidReceiveStatus", reason.name)
            when(reason?.name){
                "ImageMissing"-> manageFragments(ThirdFragment())
                "DocExpired"->manageFragments(DocumentExpired())
                "DocNotSupported"->manageFragments(DocumentUnsupported())
                "DocDamaged"->manageFragments(DamagedDocument())
                "PoorImageQuality"->manageFragments(ThirdFragment())
            }
        }
        else {
            Log.d("verificationDidReceiveStatus", "verificationRequiresRecapture")
        }

        }


    override fun start() {
        viewModel.getAccessToken("https://api.transmitsecurity.io","client_credentials",Constants.CLIENT_ID,  Constants.CLIENT_SECRET)
    }

    private fun manageFragments(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = fragment
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}


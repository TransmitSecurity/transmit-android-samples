package com.transmitsecurity.identityverificationbasic


import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.transmit.identityverification.ITSIdentityVerificationStatus
import com.transmit.identityverification.TSIdentityVerification
import com.transmit.identityverification.TSIdentityVerificationError
import com.transmit.identityverification.TSRecaptureReason
import com.transmitsecurity.identityverificationbasic.databinding.ActivityMainBinding
import com.transmitsecurity.identityverificationbasic.viewmodel.IdvViewModel

class MainActivity : AppCompatActivity(), ITSIdentityVerificationStatus,PreparationFragmentActions {

    private val viewModel: IdvViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private  var accessToken :String?=  null
    private lateinit var startToken :String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = MainScreen()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()



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

    override fun start() {
        viewModel.getAccessToken("https://api.transmitsecurity.io","client_credentials",Constants.CLIENT_ID,  Constants.CLIENT_SECRET)
    }


    override fun verificationStartCapturing() {
        Log.d("verificationDidReceiveStatus", "verificationCapturing")
    }

    override fun verificationStartProcessing() {
        Log.d("verificationDidReceiveStatus", "verificationStartProcessing")
        changeStatusFragment("In process")
    }

    //when verification status is completed, you can obtain the verification result via backend request (step 9).
    override fun verificationCompleted() {
        Log.d("verificationDidReceiveStatus", "verificationCompleted")
        changeStatusFragment("Completed")
    }

    override fun verificationCanceled() {
        Log.d("verificationDidReceiveStatus", "verificationCanceled")
        Toast.makeText(this,"Previous verification was canceled", Toast.LENGTH_SHORT).show();
    }

    override fun verificationFail(error: TSIdentityVerificationError) {
        Log.d("verificationDidFail", error.name)
        when(error.name){
            "CameraPermissionRequired" -> changeStatusFragment("Error, camera permissions required")
            "SdkDisabled" ->changeStatusFragment("Error, sdk is disabled")
            "SessionNotValid" ->changeStatusFragment("Error, session is not valid")
            "VerificationStatusError" ->changeStatusFragment("Error, Verification status error")

        }
    }

    override fun verificationRequiresRecapture(reason: TSRecaptureReason?) {
        TSIdentityVerification.recapture(this)
        if (reason != null) {
            Log.d("verificationDidReceiveStatus", reason.name)
            when (reason.name) {
            "ImageMissing"-> changeStatusFragment("Recapture, image is missing")
            "DocExpired"-> changeStatusFragment("Recapture, document is expired")
            "DocNotSupported"-> changeStatusFragment("Recapture, document is not supported")
            "DocDamaged"-> changeStatusFragment("Recapture, document is damaged")
            "PoorImageQuality"-> changeStatusFragment("Recapture, poor image quality")
        }
            }

                else {
                Log.d("verificationDidReceiveStatus", "verificationRequiresRecapture")
            }
    }

    private fun changeStatusFragment(status:String){

        // Create an instance of your fragment
       val fragment= StatusScreen.newInstance(status)

        // Get the fragment manager and start a transaction
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Add the fragment to the container view
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)

        // Commit the transaction
        fragmentTransaction.commit()

    }



}
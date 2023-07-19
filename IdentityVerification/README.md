# Identity Verification

This Transmit sample app demonstrates how to use Identity Verification service in your android app.
You can read more about Transmit's Identity Verification [here](https://developer.transmitsecurity.com/guides/verify/identity_verification_overview/).

## Prerequisites

To integrate with Transmit, you'll need to configure an application, see more details about it [here](https://developer.transmitsecurity.com/guides/verify/quick_start_android/). 

## Instructions

To run the sample on your android device:  

1 - Configure your client credentials in the `MyApplication.kt` file and init the SDK:
```bash
TSIdentityVerification.initialize("CLIENT_ID")  # Client ID obtained from the Admin Portal
```
When your app configuration is based on EU, override the default base url with the appropriate one as part of the initialization process. For example:
 ```bash
 TSIdentityVerification.initialize("https://api.eu.transmitsecurity.io/verify", "CLIENT_ID") # Client ID obtained from the Admin Portal
```
2 - Build and run the application in Android Studio on your android device target.

## Additional Steps

1 - To observe status changes, register for the status by calling registerForStatus on onStart of the MainActivity class of your app:
```bash
 TSIdentityVerification.registerForStatus(this)
```
2 -  Implement the ITSIdentityVerificationStatus:
```bash
private class IDVStatusObserver: TSIdentityVerificationDelegate {
    
    override fun verificationStartCapturing() {
        ...
    }
    
    override fun verificationStartProcessing() {
        ...
    }
    
    override fun verificationRequiresRecapture(reason: TSRecaptureReason?) {
        ...
    }
    
    override fun verificationCompleted() {
        ...
    }

   override fun verificationFail(error: TSIdentityVerificationError) {
        ...
    }
}
```
3 - Add camera permission
                

## What is Identity Verification?
Identity verification is the process of verifying that a user is the person they claim to be. In the digital world, this means tying a digital identity with the real-world identity. This typically involves verifying their ID document (like driver's license or passport), comparing a selfie to their photo ID, and various other checks.
You can use identity verification to securely verify the identity of your customers using documents like their driver’s license or passport—such as before allowing them to open a new bank account online or pick up a rental car.<br><br>

## Author

Transmit Security, https://github.com/TransmitSecurity

## License

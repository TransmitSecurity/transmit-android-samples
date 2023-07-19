package com.transmitsecurity.idvsdkdemoapp.network

import com.transmitsecurity.idvsdkdemoapp.network.helper.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
IMPORTANT: Mock Serve Provider Class Usage Notice

Please note that the "MockServerProvider" class included in this sample app serves as a demonstration tool and should not be used in a production environment. This class has been created to simulate server-side functionality for showcasing the usage of the SDK.

In a production environment, the app's server should be responsible for handling the communication between the app and Transmit's server. The app's server acts as an intermediary, facilitating secure and optimized communication with our server, which provides the actual SDK functionality.

When integrating the SDK into your own application, it is essential to adhere to the recommended communication flow. Your app's server should handle the communication with Transmit's server.
 */

class MockServerProvider {

    fun getAccessToken(baseUrl: String, getAccessTokenRequest: GetAccessTokenRequest, onResult: (GetAccessTokenResponse?) -> Unit){
        val retrofit = ServiceBuilder.buildService(baseUrl, GetAccessTokenService::class.java)
        retrofit.getAccessToken( getAccessTokenRequest.grantType, getAccessTokenRequest.clientId, getAccessTokenRequest.clientSecret)
            .enqueue(
                object : Callback<GetAccessTokenResponse> {
                    override fun onFailure(call: Call<GetAccessTokenResponse>, t: Throwable) {
                        onResult(null)
                    }
                    override fun onResponse(call: Call<GetAccessTokenResponse>, response: Response<GetAccessTokenResponse>) {
                        val getAccessTokenResponse = response.body()
                        onResult(getAccessTokenResponse)
                    }
                }
            )
    }


        fun createSession(baseUrl: String, accessToken: String, onResult: (CreateSessionResponse?) -> Unit){
            val retrofit = ServiceBuilder.buildService(baseUrl, CreateSessionService::class.java)
            retrofit.createSession("Bearer $accessToken", CreateSessionRequest())
                .enqueue(
                    object : Callback<CreateSessionResponse> {
                        override fun onFailure(call: Call<CreateSessionResponse>, t: Throwable) {
                            onResult(null)
                        }
                        override fun onResponse(call: Call<CreateSessionResponse>, response: Response<CreateSessionResponse>) {
                            val getAccessTokenResponse = response.body()
                            onResult(getAccessTokenResponse)
                        }
                    }
                )

    }

}
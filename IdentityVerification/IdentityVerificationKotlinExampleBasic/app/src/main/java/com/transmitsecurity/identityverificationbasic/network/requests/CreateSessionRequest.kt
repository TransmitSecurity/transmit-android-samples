package com.transmitsecurity.identityverificationbasic.network.requests

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CreateSessionService {
    @POST("/verify/api/v1/verification")
    fun createSession(@Header("Authorization") authHeader: String, @Body reteSessionRequest: CreateSessionRequest): Call<CreateSessionResponse>
}

data class CreateSessionRequest(@SerializedName("auto_start") val autoStart: Boolean = false)
data class CreateSessionResponse(@SerializedName("start_token") val startToken: String, @SerializedName("session_id") val sessionId: String, @SerializedName("expiration") val expiration: String, @SerializedName("missing_images") val missingImages: List<String>)
package com.transmitsecurity.identityverificationbasic.network.requests

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GetAccessTokenService {
    @FormUrlEncoded
    @POST("oidc/token")
    fun getAccessToken(@Field("grant_type")  grantType: String, @Field("client_id")  clientId: String, @Field("client_secret")  clientSecret: String): Call<GetAccessTokenResponse>
}
data class GetAccessTokenRequest(@SerializedName("grant_type") val grantType: String, @SerializedName("client_id") val clientId: String, @SerializedName("client_secret") val clientSecret: String)
data class GetAccessTokenResponse(@SerializedName("access_token") val accessToken: String?, @SerializedName("expires_in") val expiresIn: Int, @SerializedName("token_type") val tokenType: String?)

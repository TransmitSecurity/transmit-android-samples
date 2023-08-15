package com.transmitsecurity.identityverificationbasic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.transmitsecurity.identityverificationbasic.network.MockServerProvider
import com.transmitsecurity.identityverificationbasic.network.requests.GetAccessTokenRequest

class IdvViewModel :ViewModel(){


    private val _accessToken = MutableLiveData<String>()

    val accessToken: LiveData<String>
        get() = _accessToken


    private val _startToken = MutableLiveData<String>()

    val startToken: LiveData<String>
        get() = _startToken

    fun getAccessToken(baseUrl:String, grant_type:String,client_id:String,client_secret:String  ){
        val getAccessApi = MockServerProvider();
        getAccessApi.getAccessToken(baseUrl, GetAccessTokenRequest(grant_type,client_id,client_secret)){
            it?.let { _accessToken.value=it.accessToken?: "" }
        }
    }

    fun createSessionId (baseUrl:String ){
        val createSessionId = MockServerProvider();
        createSessionId.createSession(baseUrl,accessToken.value?:"" ){
            it?.let { _startToken.value=it.startToken?: "" }
        }
    }


}
package com.aman.githubclone.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.githubclone.networking.Resource
import com.aman.githubclone.networking.models.request.AccessTokenRequestModel
import com.aman.githubclone.networking.models.response.AccessTokenResponseModel
import com.aman.githubclone.networking.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _getAccessTokenResponse = MutableLiveData<Resource<AccessTokenResponseModel?>>()
    val getAccessTokenResponseModelResponse : LiveData<Resource<AccessTokenResponseModel?>>
    get() = _getAccessTokenResponse

    fun getAccessToken(accessTokenRequestModel: AccessTokenRequestModel) = viewModelScope.launch {
        _getAccessTokenResponse.postValue(Resource.loading(null))
        repository.getAccessToken(accessTokenRequestModel).let {
            if (it.isSuccessful){
                _getAccessTokenResponse.postValue(Resource.success(it.body()))
            } else {
                _getAccessTokenResponse.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}
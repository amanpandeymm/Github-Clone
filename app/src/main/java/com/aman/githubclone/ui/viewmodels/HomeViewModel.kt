package com.aman.githubclone.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.githubclone.networking.Resource
import com.aman.githubclone.networking.models.response.UserRepoResponseModel
import com.aman.githubclone.networking.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository): ViewModel(){

    private val _getUserRepoResponse = MutableLiveData<Resource<UserRepoResponseModel?>>()
    val getUserRepoResponse : LiveData<Resource<UserRepoResponseModel?>>
    get() = _getUserRepoResponse

    fun getUserRepos(token: String) = viewModelScope.launch {
        _getUserRepoResponse.postValue(Resource.loading(null))
        repository.getUserRepos(token).let {
            if (it.isSuccessful){
                _getUserRepoResponse.postValue(Resource.success(it.body()))
            } else {
                _getUserRepoResponse.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}
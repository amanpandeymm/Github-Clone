package com.aman.githubclone.ui.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.githubclone.networking.Resource
import com.aman.githubclone.networking.Status
import com.aman.githubclone.networking.models.response.UserRepoResponseModel
import com.aman.githubclone.networking.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository): ViewModel(){


    val _getUserRepoResponse = MutableStateFlow<Resource<UserRepoResponseModel?>?>(null)

    fun getUserRepos(token: String) = viewModelScope.launch {
        _getUserRepoResponse.value = Resource.loading(null)
        repository.getUserRepos(token).let {
            if (it.isSuccessful){
                _getUserRepoResponse.emit(Resource.success(it.body()))
            } else {
                _getUserRepoResponse.emit(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}
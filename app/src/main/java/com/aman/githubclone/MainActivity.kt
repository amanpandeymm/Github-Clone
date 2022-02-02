package com.aman.githubclone

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aman.githubclone.networking.Status
import com.aman.githubclone.networking.models.request.AccessTokenRequestModel
import com.aman.githubclone.ui.viewmodels.AuthViewModel
import com.aman.githubclone.ui.viewmodels.HomeViewModel
import com.aman.githubclone.utils.Constants
import com.aman.githubclone.utils.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var navController: NavHostController

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpObservers()
        setContent {
            navController = rememberNavController()
            Navigation(navController = navController, homeViewModel)
        }
    }

    override fun onResume() {
        super.onResume()
        callGetAccessTokenApi()
    }

    private fun callGetAccessTokenApi() {
        val uri: Uri? = intent.data
        if (uri != null && uri.toString().startsWith(Constants.redirectUri)) {
            val code: String? = uri.getQueryParameter("code")
            authViewModel.getAccessToken(AccessTokenRequestModel(Constants.clientId, Constants.clientSecret, code))
        }
    }

    private fun setUpObservers() {

        //Get Access Token Observer
        authViewModel.getAccessTokenResponseModelResponse.observe(this@MainActivity) {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { response ->
                            homeViewModel.getUserRepos("Bearer ${response.accessToken}")
                        }
                    }
                    Status.ERROR -> { Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show() }
                    Status.LOADING -> {}
                }
            }
        }

        lifecycleScope.launch {
            homeViewModel._getUserRepoResponse.collect(){
                it.let { resource ->
                    when (resource?.status) {
                        Status.SUCCESS -> {
                            resource?.data?.let { response ->
                                navController.navigate("home_screen")
                            }
                        }
                        Status.ERROR -> {}
                        Status.LOADING -> {}
                    }
                }
            }
        }

        //Get User Repo Observer

    }

}










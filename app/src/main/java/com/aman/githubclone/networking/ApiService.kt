package com.aman.githubclone.networking

import com.aman.githubclone.networking.models.request.AccessTokenRequestModel
import com.aman.githubclone.networking.models.response.AccessTokenResponseModel
import com.aman.githubclone.networking.models.response.UserRepoResponseModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    suspend fun getAccessToken(@Body accessTokenRequestModel: AccessTokenRequestModel) : Response<AccessTokenResponseModel>

    @GET("user/repos")
    suspend fun getUserRepos(@Header("Authorization") token: String) : Response<UserRepoResponseModel>
}
package com.aman.githubclone.networking.repository

import com.aman.githubclone.networking.ApiService
import com.aman.githubclone.networking.models.request.AccessTokenRequestModel
import retrofit2.http.Field
import javax.inject.Inject
import javax.inject.Named

class Repository @Inject constructor(
    @Named("githubRetrofitApiService") private val githubApiService: ApiService,
    @Named("apiGithubRetrofitApiService") private val apiGithubApiService: ApiService
) {

    suspend fun getAccessToken(accessTokenRequestModel: AccessTokenRequestModel) = githubApiService
        .getAccessToken(accessTokenRequestModel)

    suspend fun getUserRepos(token: String) = apiGithubApiService
        .getUserRepos(token)
}
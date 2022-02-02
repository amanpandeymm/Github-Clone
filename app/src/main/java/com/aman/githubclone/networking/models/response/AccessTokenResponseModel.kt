package com.aman.githubclone.networking.models.response

import com.google.gson.annotations.SerializedName

data class AccessTokenResponseModel(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("scope") val scope: String,
)

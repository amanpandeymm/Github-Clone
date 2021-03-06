package com.aman.githubclone.networking.models.request

import com.google.gson.annotations.SerializedName

data class AccessTokenRequestModel(
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String,
    @SerializedName("code") val code: String?,
)

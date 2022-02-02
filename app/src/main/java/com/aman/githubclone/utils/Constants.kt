package com.aman.githubclone.utils

class Constants {
    companion object {
        const val BASE_URL_GITHUB = "https://github.com/"
        const val BASE_URL_API_GITHUB = "https://api.github.com/"
        const val clientId = "7fb12d5a77f9519a3003"
        const val clientSecret = "fa0c80153b6d2f50c5dd99edc112ce5b44a0ecfb"
        const val redirectUri = "githubclone://callback"

        const val requestUserIdentityUri =
            BASE_URL_GITHUB + "login/oauth/authorize" + "?client_id=" + clientId + "&scope=repo" + "&redirect_uri=" + redirectUri
    }
}
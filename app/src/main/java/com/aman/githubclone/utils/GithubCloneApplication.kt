package com.aman.githubclone.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GithubCloneApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}
package com.aman.githubclone.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aman.githubclone.networking.models.response.UserRepoResponseModel
import com.aman.githubclone.ui.screens.AuthScreenUI
import com.aman.githubclone.ui.screens.HomeScreenUI
import com.aman.githubclone.ui.screens.SplashScreenUI
import com.aman.githubclone.ui.viewmodels.HomeViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreenUI(navController = navController)
        }
        composable("auth_screen") {
            AuthScreenUI()
        }
        composable(route = "home_screen", arguments = listOf(
            navArgument("repoList") {
                type = NavType.SerializableType(UserRepoResponseModel::class.java)
            }
        )) {
            val repoList =
                (navController.previousBackStackEntry?.arguments?.getSerializable("repoList") as? UserRepoResponseModel)
                    ?: return@composable
            HomeScreenUI(repoList = repoList)
        }
    }
}
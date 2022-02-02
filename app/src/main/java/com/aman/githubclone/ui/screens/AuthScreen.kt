package com.aman.githubclone.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aman.githubclone.R.*
import com.aman.githubclone.utils.Constants

@Composable
fun AuthScreenUI() {

    val context = LocalContext.current
    val githubLoginIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.requestUserIdentityUri))

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = rememberScaffoldState(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(50.dp)
        ) {

            Image(
                painter = painterResource(id = drawable.ic_github),
                contentDescription = "Github Logo",
                Modifier.scale(4.0f).weight(1f)
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                onClick = {
                    context.startActivity(githubLoginIntent)
                }
            ) {
                Text(text = "SIGN IN WITH GITHUB", fontFamily = FontFamily.SansSerif)
            }
        }
    }
}
package com.aman.githubclone.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import coil.compose.rememberImagePainter
import com.aman.githubclone.networking.Resource
import com.aman.githubclone.networking.Status
import com.aman.githubclone.networking.models.response.UserRepoResponseModel
import com.aman.githubclone.networking.models.response.UserRepoResponseModelItem
import com.aman.githubclone.ui.viewmodels.HomeViewModel


@Composable
fun HomeScreenUI(homeViewModel: HomeViewModel = hiltViewModel()) {
    val userRepoResponse by homeViewModel._getUserRepoResponse.collectAsState()
    userRepoResponse?.data?.let {
        
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            itemsIndexed(items = it) { index, item ->
                RepoItemUI(repoItem = item)
            }
        }
    } ?: run {
        if(userRepoResponse?.message.isNullOrEmpty()) Text(text = "HomeScreen")
    }
    
}


//ItemLayout
@Composable
fun RepoItemUI(repoItem: UserRepoResponseModelItem) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = repoItem.owner.avatar_url,
                    ),
                    contentDescription = repoItem.owner.gravatar_id,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = repoItem.owner.login,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = repoItem.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }

}
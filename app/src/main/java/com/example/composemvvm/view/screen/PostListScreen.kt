package com.example.composemvvm.view.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composemvvm.data.ResultState
import com.example.composemvvm.model.Post
import com.example.composemvvm.ui.theme.ComposeMVVMTheme
import com.example.composemvvm.utils.flowInLifecycle

@Composable
fun PostListScreen() {
    val mainViewModel = hiltViewModel<PostViewModel>()

    mainViewModel.getAllPost()

    val postListState by mainViewModel.postListState.flowInLifecycle()
        .collectAsState(initial = null)

    val postListData by mainViewModel.postListData.flowInLifecycle()
        .collectAsState(initial = emptyList())

    Surface(color = MaterialTheme.colors.background) {
        when (postListState) {
            is ResultState.Success -> {
                ShowListWithData(postListData = postListData)
            }
        }
    }
}

@Composable
private fun ShowListWithData(postListData: List<Post>) {
    LazyColumn(modifier = Modifier.fillMaxHeight())
    {
        itemsIndexed(postListData) { _, item ->
            Card(
                elevation = 1.dp,
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier.padding(5.dp)
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = item.title,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(text = item.body)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeMVVMTheme {
        ShowListWithData(
            listOf(
                Post(1, 1, "title1", "body1"),
                Post(2, 2, "title1", "body2"),
                Post(3, 3, "title1", "body3"),
                Post(4, 4, "title1", "body4"),
                Post(5, 5, "title1", "body5"),
                Post(6, 6, "title6", "body6"),
            )
        )
    }
}
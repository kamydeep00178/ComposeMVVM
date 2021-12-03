package com.example.composemvvm.view.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemvvm.data.PostRepository
import com.example.composemvvm.data.ResultState
import com.example.composemvvm.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : ViewModel() {

    private val _postListState = MutableStateFlow<ResultState<List<Post>>?>(null)
    val postListState = _postListState.asStateFlow()

    private val _postListData = MutableStateFlow<List<Post>>(emptyList())
    val postListData = _postListData.asStateFlow()

    fun getAllPost() {
        viewModelScope.launch {
            postRepository.getAllPosts()
                .catch {
                    _postListState.value = ResultState.Error(it.message)
                }
                .collect {
                    if (it is ResultState.Success) {
                        _postListState.value = ResultState.Success(it.data)
                        _postListData.value = it.data
                    }
                }
        }
    }
}
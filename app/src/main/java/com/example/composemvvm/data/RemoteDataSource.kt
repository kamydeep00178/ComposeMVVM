package com.example.composemvvm.data

import com.example.composemvvm.api.AppService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val appService: AppService) {
    suspend fun getPostData() = appService.getPosts()
}
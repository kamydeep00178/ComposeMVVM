package com.example.composemvvm.api

import com.example.composemvvm.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface AppService {
    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>
}
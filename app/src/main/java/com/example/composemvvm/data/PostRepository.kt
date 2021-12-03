package com.example.composemvvm.data

import com.example.composemvvm.model.Post
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class PostRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {
    suspend fun getAllPosts(): Flow<ResultState<List<Post>>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getPostData() })
        }.flowOn(Dispatchers.IO)
    }
}
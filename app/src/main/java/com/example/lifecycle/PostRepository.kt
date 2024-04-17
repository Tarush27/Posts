package com.example.lifecycle

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepository(val postService: PostService, val postDatabase: PostDatabase) {

    private val _postResponse = MutableLiveData<List<Post>?>()
    val postResponse: LiveData<List<Post>?> = _postResponse

    suspend fun getPosts() {

        try {
            val response = postService.getPosts()
            Log.d("repo", "getPosts: ${response.body()!!}")
            if (response.body() != null) {
                _postResponse.value = response.body()
                _postResponse.value = response.body()
                postDatabase.postDao().insertPosts(response.body()!!)
            } else {
                _postResponse.value = null
            }
        } catch (e: Exception) {
            _postResponse.value = null
        }

    }

}


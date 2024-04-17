package com.example.lifecycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

    val posts = postRepository.postResponse

    fun getPosts() {
        viewModelScope.launch {
            postRepository.getPosts()
        }
    }

}
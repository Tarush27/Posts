package com.example.lifecycle

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.lifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var postViewModel: PostViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private lateinit var postDatabase: PostDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPostRecyclerView()

        postDatabase = PostDatabase.getPostDatabase(this)!!
        postViewModel = ViewModelProvider(
            this, PostViewModelFactory(PostRepository(RetrofitClient.service, postDatabase))
        )[PostViewModel::class.java]

        binding.swipeRl.setOnRefreshListener(swipeRefreshListener)

        getAllPosts()
    }

    private fun getAllPosts() {
        postViewModel.posts.observe(this) { posts -> }

        postViewModel.posts.observe(this) { posts ->

            if (posts != null) {

                Log.d("mainactivity", "getAllPosts: $posts")
                postAdapter.updatePosts(posts as ArrayList<Post>)
                binding.loading.root.visibility = View.GONE
                binding.noInternet.root.visibility = View.GONE
                binding.postsRv.visibility = View.VISIBLE
                binding.postsTv.visibility = View.VISIBLE
                binding.swipeRl.isRefreshing = false
            } else {
                binding.noInternet.root.visibility = View.VISIBLE
                binding.postsRv.visibility = View.GONE
                binding.swipeRl.isRefreshing = false
                binding.loading.root.visibility = View.GONE
                binding.postsTv.visibility = View.GONE
            }
        }

        postViewModel.getPosts()
    }


    private fun setupPostRecyclerView() {
        binding.postsRv.layoutManager = GridLayoutManager(
            this, 2
        )
        postAdapter = PostAdapter()
        binding.postsRv.adapter = postAdapter
    }

    private val swipeRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        binding.swipeRl.isRefreshing = true
        postViewModel.getPosts()
    }

}
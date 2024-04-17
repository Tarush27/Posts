package com.example.lifecycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecycle.databinding.SinglePostItemBinding

class PostAdapter() : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private val posts: ArrayList<Post> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PostViewHolder {
        val binding = SinglePostItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PostViewHolder(binding)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val post = posts[position]

        holder.bindPost(post)

    }

    fun updatePosts(updatedPosts: ArrayList<Post>) {
        posts.addAll(updatedPosts)
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val binding: SinglePostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPost(post: Post) {
            binding.titleTv.text = post.title
            binding.bodyTv.text = post.body
        }

    }

}
package com.example.rxjavaflatmapexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaflatmapexample.databinding.LayoutPostListItemBinding
import com.example.rxjavaflatmapexample.models.Post
import java.util.ArrayList


class PostAdapter : ListAdapter<Post, PostAdapter.MyViewHolder>(PostDiffCallback()) {

    private lateinit var binding: LayoutPostListItemBinding
    private var posts: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = LayoutPostListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPost(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    fun updatePost(post: Post) {
        val array = toArray(posts)
        array[posts.indexOf(post)] = post
        notifyItemChanged(posts.indexOf(post))
    }

    private fun toArray(posts: List<Post>): MutableList<Post> {
        return posts.toMutableList()
    }

    inner class MyViewHolder(binding: LayoutPostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val title = binding.title
        private val numComments = binding.numComments
        private val progressBar = binding.progressBar

        fun bind(post: Post) {
            title.text = post.title

            if(post.comments == null) {
                showProgressBar(true)
                numComments.text = ""
            }

            else {
                showProgressBar(false)
                numComments.text = ((post.comments)?.size).toString()
            }
        }

        private fun showProgressBar(showProgressBar: Boolean) {
            if (showProgressBar) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.title == newItem.title
}
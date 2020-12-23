package com.example.rxjavaflatmapexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaflatmapexample.databinding.LayoutPostListItemBinding
import com.example.rxjavaflatmapexample.models.Post
import java.util.*


class PostAdapter : ListAdapter<Post, PostAdapter.MyViewHolder>(PostDiffCallback()) {

    private lateinit var binding: LayoutPostListItemBinding
    private var posts: List<Post> = ArrayList()
    private var listener: OnPostClickListener? = null

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

    fun getPosts(): List<Post> {
        return posts
    }

    private fun toArray(posts: List<Post>): MutableList<Post> {
        return posts.toMutableList()
    }

    inner class MyViewHolder(binding: LayoutPostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val title = binding.title

        init {
            title.setOnClickListener {
                listener?.onPostClick(adapterPosition)
            }
        }

        fun bind(post: Post) {
            title.text = post.title
        }

    }

    interface OnPostClickListener {
        fun onPostClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnPostClickListener) {
        this.listener = listener
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
        oldItem.title == newItem.title
}
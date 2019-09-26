package com.example.todo_app.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.Model.Post
import com.example.todo_app.R

class PostViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
    private var authorView: TextView = itemView.findViewById(R.id.post_AuthorName)
    private var messageView: TextView = itemView.findViewById(R.id.post_Message)

    fun bind(post: Post) {
        authorView.text = post.authorName
        messageView.text = post.message
    }
}
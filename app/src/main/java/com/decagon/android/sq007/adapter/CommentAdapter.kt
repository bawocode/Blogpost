package com.decagon.android.sq007.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.datamodel.CommentDataItem

class CommentAdapter() : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    private var commentList = emptyList<CommentDataItem>()

    inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var userName: TextView = view.findViewById(R.id.name)
        var userEmail: TextView = view.findViewById(R.id.email)
        var commentBody: TextView = view.findViewById(R.id.comment_body)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentViewHolder {
        return CommentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comment_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val currentComment = commentList[position]
        holder.userName.text = currentComment.name
        holder.commentBody.text = currentComment.body
        holder.userEmail.text = currentComment.email
        
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    fun setCommentData(newCommentList: List<CommentDataItem>) {
        commentList = newCommentList
        notifyDataSetChanged()

    }
}
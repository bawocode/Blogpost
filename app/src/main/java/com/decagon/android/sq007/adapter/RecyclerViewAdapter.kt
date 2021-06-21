package com.decagon.android.sq007.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.R
import com.decagon.android.sq007.datamodel.PostDataItem
import com.decagon.android.sq007.network.OnClickPostListener

class RecycleViewAdapter(var listener: OnClickPostListener) : RecyclerView.Adapter<RecycleViewAdapter.PostViewHolder>() {
    private var postList = ArrayList<PostDataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_view_layout, parent, false) )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentPost = postList[position]
        holder.postTitle.text = currentPost.title
        holder.postBody.text = currentPost.body
        holder.userid.text = currentPost.userId.toString()
        holder.itemView.setOnClickListener {
            listener.onClickPost(postList[position].userId)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var postTitle: TextView = view.findViewById(R.id.post_title)
        var postBody: TextView = view.findViewById(R.id.post_body)
        var userid: TextView = view.findViewById(R.id.user_id)
    }

    fun setPostData(newPostList: List<PostDataItem>){
        postList = newPostList as ArrayList<PostDataItem>
        notifyDataSetChanged()

    }
     fun addPost(arrayListPost: ArrayList<PostDataItem>){
         postList.addAll(0,arrayListPost)
         notifyItemInserted(0)
         notifyDataSetChanged()

     }


}
package com.decagon.android.sq007.repository

import com.decagon.android.sq007.datamodel.CommentDataItem
import com.decagon.android.sq007.datamodel.PostDataItem
import com.decagon.android.sq007.network.RetrofitInstance
import retrofit2.Response

class PostRepository {
    suspend fun getPost():Response<List<PostDataItem>>{
       return RetrofitInstance.api.getPostLists()
    }

    suspend fun getComment(postId: Int): Response<List<CommentDataItem>>{
        return RetrofitInstance.api.getPostComment(postId)
    }

    suspend fun addPost(title:String, body:String, userId:Int): Response<List<PostDataItem>>{
        return RetrofitInstance.api.addPost(title, body, userId)
    }
}
package com.decagon.android.sq007.network

import androidx.lifecycle.LiveData
import com.decagon.android.sq007.datamodel.CommentDataItem
import com.decagon.android.sq007.datamodel.PostDataItem
import retrofit2.Response
import retrofit2.http.*

interface RetroServiceInterface {

    @GET("posts")
    suspend fun getPostLists(): Response<List<PostDataItem>>

    @GET("posts/{id}/comments")
    suspend fun getPostComment(@Path("id")postId: Int):Response<List<CommentDataItem>>

    @POST("posts")
    suspend fun addPost(
       @Field("title")title:String,
       @Field("body")body:String,
       @Field("userId")userId:Int
    ): Response<List<PostDataItem>>



}
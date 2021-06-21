package com.decagon.android.sq007.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagon.android.sq007.datamodel.CommentDataItem
import com.decagon.android.sq007.datamodel.PostDataItem
import com.decagon.android.sq007.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.POST

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    var postList: MutableLiveData<Response<List<PostDataItem>>> = MutableLiveData()
    var commentList: MutableLiveData<Response<List<CommentDataItem>>> = MutableLiveData()
    var addPostList: MutableLiveData<ArrayList<PostDataItem>> = MutableLiveData()

    fun addPostList(post: PostDataItem){
        viewModelScope.launch {

            try {
                val response = repository.addPost("New Post", "This is my post", 1)
                addPostList.value!!.add(post)

            } catch (e : Exception){
                Log.i("Error", e.localizedMessage)
            }

        }
    }


    fun getPostList() {
        viewModelScope.launch {
            val response = repository.getPost()
            postList.value = response
        }

    }

    fun getComment(postId: Int){
        viewModelScope.launch {
            val commentResponse = repository.getComment(postId)
            commentList.value = commentResponse
        }
    }
}
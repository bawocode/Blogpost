package com.decagon.android.sq007

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.android.sq007.adapter.CommentAdapter
import com.decagon.android.sq007.network.OnClickPostListener
import com.decagon.android.sq007.repository.PostRepository
import com.decagon.android.sq007.viewmodel.PostViewModel
import com.decagon.android.sq007.viewmodel.ViewModelFactory

class CommentActivity : AppCompatActivity() {
    lateinit var commentRecyclerView: RecyclerView
    private val commentAdapter by lazy {
        CommentAdapter()
    }
    lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        commentRecyclerView = findViewById(R.id.comment_recycler_view)

        setCommentRecyclerView()

        val repository = PostRepository()
        val repoFactory = ViewModelFactory(repository)

        postViewModel = ViewModelProvider(this, repoFactory).get(PostViewModel::class.java)

        val intent = intent
        val id = intent.getIntExtra("id",0)
        postViewModel.getComment(id)
        postViewModel.commentList.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { commentAdapter.setCommentData(it) }
                Log.d("Comment", response.body().toString())
            } else {
                Log.d("Error", response.code().toString())
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setCommentRecyclerView() {
        commentRecyclerView.adapter = commentAdapter
        commentRecyclerView.layoutManager = LinearLayoutManager(this)
    }



}
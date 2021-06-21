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
import com.decagon.android.sq007.adapter.RecycleViewAdapter
import com.decagon.android.sq007.datamodel.PostDataItem
import com.decagon.android.sq007.network.OnClickPostListener
import com.decagon.android.sq007.repository.PostRepository
import com.decagon.android.sq007.viewmodel.PostViewModel
import com.decagon.android.sq007.viewmodel.ViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), OnClickPostListener {
    lateinit var addPostButton: FloatingActionButton
    lateinit var postRecyclerView: RecyclerView
    private val postAdapter by lazy {
        RecycleViewAdapter(this)
    }
    lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         addPostButton = findViewById(R.id.add_posst)

        addPostButton.setOnClickListener {
            val intent = Intent(this, AddPostActivity::class.java)
            startActivity(intent)
        }

        postRecyclerView = findViewById(R.id.recyclerview)

        setPostRecyclerView()


        val repository = PostRepository()
         val repoFactory = ViewModelFactory(repository)

        postViewModel = ViewModelProvider(this, repoFactory).get(PostViewModel::class.java)
        val myPost = PostDataItem("This is my new post", 2, "New Post", 4)
        postViewModel.addPostList(myPost)
        postViewModel.getPostList()

        postViewModel.addPostList.observe(this, Observer {
            postAdapter.addPost(it)
            Log.d("addResponse", it.toString())
        })


        postViewModel.postList.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { postAdapter.setPostData(it) }
                Log.d("Response", response.body().toString())
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setPostRecyclerView() {
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClickPost(id: Int) {
        val intent = Intent(this, CommentActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
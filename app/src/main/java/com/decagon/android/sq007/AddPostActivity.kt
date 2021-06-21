package com.decagon.android.sq007

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.decagon.android.sq007.repository.PostRepository
import com.decagon.android.sq007.viewmodel.PostViewModel

class AddPostActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        val createPost: Button = findViewById(R.id.create_post_button)
        val repo = PostRepository()
        val post = PostViewModel(repo)

        createPost.setOnClickListener {
            post.addPostList
        }
    }
}
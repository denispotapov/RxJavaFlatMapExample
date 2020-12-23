package com.example.rxjavaflatmapexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavaflatmapexample.databinding.ActivityViewPostBinding


class ViewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_post)
        binding = ActivityViewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra("post")) {
            val post = intent.getStringExtra("post")
            binding.textView.text = post
        }

    }
}

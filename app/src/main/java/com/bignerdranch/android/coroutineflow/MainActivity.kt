package com.bignerdranch.android.coroutineflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.coroutineflow.databinding.ActivityMainBinding
import com.bignerdranch.android.coroutineflow.lesson.lesson2.UsersActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonUsersActivity.setOnClickListener {
            startActivity(UsersActivity.newIntent(this))
        }
    }
}
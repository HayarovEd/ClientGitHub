package com.edurda77.ClientGitHub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edurda77.ClientGitHub.databinding.ActivityMainBinding
import com.edurda77.ClientGitHub.databinding.ActivityReposGitHubBinding

class ReposGitHubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReposGitHubBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReposGitHubBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setRecycledView()
    }
}
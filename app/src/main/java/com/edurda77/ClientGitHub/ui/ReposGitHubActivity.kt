package com.edurda77.ClientGitHub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edurda77.ClientGitHub.databinding.ActivityMainBinding
import com.edurda77.ClientGitHub.databinding.ActivityReposGitHubBinding
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import com.edurda77.ClientGitHub.model.RepoGitHubModel

class ReposGitHubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReposGitHubBinding
    //private val gitHubRepoUseCase: GitHubRepoUseCase by lazy { app.gitHubRepoUseCase }
    //private val reposOfUser = emptyList<RepoGitHubModel>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReposGitHubBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setRecycledView()
    }
}
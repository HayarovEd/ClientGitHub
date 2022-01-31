package com.edurda77.ClientGitHub.domain

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.edurda77.ClientGitHub.model.RepoGitHubModel

interface GitHubRepoUseCase {
    @WorkerThread
    @Throws(Throwable::class)
    fun getReposForGitHub (userName: String): LiveData<List<RepoGitHubModel>>
}
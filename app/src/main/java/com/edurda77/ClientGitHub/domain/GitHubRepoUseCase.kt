package com.edurda77.ClientGitHub.domain

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.edurda77.ClientGitHub.model.RepoGitHubModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface GitHubRepoUseCase {
    @WorkerThread
    @Throws(Throwable::class)
    fun getReposForGitHub(userName: String): List<RepoGitHubModel>?
    fun getReposObservable(userName: String): Observable<List<RepoGitHubModel>>

}
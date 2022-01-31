package com.edurda77.ClientGitHub.domain

import androidx.lifecycle.LiveData
import com.edurda77.ClientGitHub.model.RepoGitHubModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubRepoApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): LiveData<List<RepoGitHubModel>>
}
package com.edurda77.ClientGitHub.domain

import com.edurda77.ClientGitHub.model.RepoGitHubModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubRepoApi {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<RepoGitHubModel>>
    @GET("users/{user}/repos")
    fun listReposObservable(@Path("user") user: String?): Observable<List<RepoGitHubModel>>
}
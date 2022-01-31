package com.edurda77.ClientGitHub.model

import androidx.lifecycle.LiveData
import com.edurda77.ClientGitHub.domain.GitHubRepoApi
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "api.github.com/"

class RetrofitGitHubUseCaseImpl : GitHubRepoUseCase {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var api = retrofit.create(GitHubRepoApi::class.java)

    override fun getReposForGitHub(userName: String): LiveData<List<RepoGitHubModel>> {
        return api.listRepos(userName)
    }



}
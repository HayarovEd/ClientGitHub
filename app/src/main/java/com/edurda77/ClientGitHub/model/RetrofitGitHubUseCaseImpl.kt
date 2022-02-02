package com.edurda77.ClientGitHub.model

import com.edurda77.ClientGitHub.domain.GitHubRepoApi
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.github.com/"

class RetrofitGitHubUseCaseImpl : GitHubRepoUseCase {
    private var repos = emptyList<RepoGitHubModel>().toMutableList()

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var api = retrofit.create(GitHubRepoApi::class.java)


    override fun getReposForGitHub(userName: String): List<RepoGitHubModel>? {
        return api.listRepos(userName).execute().body()
    }

    override fun getReposObservable(userName: String): List<RepoGitHubModel> {
        api.listReposObservable(userName)
        return repos

    }

    

}
package com.edurda77.ClientGitHub.model

import com.edurda77.ClientGitHub.domain.GitHubRepoApi
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.internal.util.HalfSerializer.onError
import io.reactivex.rxjava3.internal.util.HalfSerializer.onNext
import io.reactivex.rxjava3.internal.util.NotificationLite.subscription
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscriber
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.github.com/"

class RetrofitGitHubUseCaseImpl : GitHubRepoUseCase {
    val rxAdapter = RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io())
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(rxAdapter)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var api = retrofit.create(GitHubRepoApi::class.java)

    override fun getReposForGitHub(userName: String): List<RepoGitHubModel>? {
        return api.listRepos(userName).execute().body()
    }

    override fun getReposObservable(userName: String): List<RepoGitHubModel> {
        api.listReposObservable(userName)
            .flatMap {  }

    }


}
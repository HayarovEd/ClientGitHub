package com.edurda77.ClientGitHub.ui

import android.app.Application
import android.content.Context
import com.edurda77.ClientGitHub.domain.GitHubRepoApi
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import com.edurda77.ClientGitHub.model.BASE_URL
import com.edurda77.ClientGitHub.model.RetrofitGitHubUseCaseImpl
import com.edurda77.ClientGitHub.model.UserModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    fun getUsers(): List<UserModel> {
        val userOfGitHub = emptyList<UserModel>().toMutableList()
        userOfGitHub.add(UserModel("HayarovEd"))
        userOfGitHub.add(UserModel("kshalnov"))
        userOfGitHub.add(UserModel("robertBadamshin"))
        return userOfGitHub
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val api: GitHubRepoApi by lazy {
        retrofit.create(GitHubRepoApi::class.java)
    }
    val gitHubRepoUseCase: GitHubRepoUseCase by lazy {
        RetrofitGitHubUseCaseImpl(api)
    }
}

val Context.app
    get() = applicationContext as App
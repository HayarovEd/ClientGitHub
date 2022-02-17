package com.edurda77.ClientGitHub.ui

import android.app.Application
import android.content.Context

class App : Application() {

    /*fun getUsers(): List<UserModel> {
        val userOfGitHub = emptyList<UserModel>().toMutableList()
        userOfGitHub.add(UserModel("HayarovEd"))
        userOfGitHub.add(UserModel("kshalnov"))
        userOfGitHub.add(UserModel("robertBadamshin"))
        return userOfGitHub
    }*/


    /*private val retrofit: Retrofit by lazy {
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
    }*/
}

val Context.app
    get() = applicationContext as App
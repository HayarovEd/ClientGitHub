package com.edurda77.ClientGitHub.model

import android.app.Activity
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edurda77.ClientGitHub.domain.GitHubRepoApi
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import com.edurda77.ClientGitHub.model.Utility.isInternetAvailable
import com.edurda77.ClientGitHub.ui.app
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserGitHubViewModel(
    val activity: Activity
    ) : ViewModel() {
    private var listData = MutableLiveData<List<RepoGitHubModel>>()
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val api = retrofit.create(GitHubRepoApi::class.java)

    fun getData(userName: String): MutableLiveData<List<RepoGitHubModel>> {
        val retrofitGitHubUseCaseImpl = RetrofitGitHubUseCaseImpl(api)
        if (activity.applicationContext.isInternetAvailable()) {
            listData = retrofitGitHubUseCaseImpl.getMutableLiveData(activity.applicationContext, userName)
        }
        return listData
    }

}

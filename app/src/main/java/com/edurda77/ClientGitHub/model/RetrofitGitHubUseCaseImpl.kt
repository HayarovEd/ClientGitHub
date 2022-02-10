package com.edurda77.ClientGitHub.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.edurda77.ClientGitHub.domain.GitHubRepoApi
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import com.edurda77.ClientGitHub.model.Utility.hideProgressBar
import com.edurda77.ClientGitHub.model.Utility.showErrorToast
import com.edurda77.ClientGitHub.model.Utility.showProgressBar
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.github.com/"

class RetrofitGitHubUseCaseImpl : GitHubRepoUseCase {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private var api = retrofit.create(GitHubRepoApi::class.java)


    override fun getReposForGitHub(userName: String): List<RepoGitHubModel> {
        return api.listRepos(userName).execute().body()!!
    }

    override fun getReposObservable(userName: String): Observable<List<RepoGitHubModel>> {
        return api.listReposObservable(userName)
    }
    fun getMutableLiveData(context: Context, userName: String) : MutableLiveData<List<RepoGitHubModel>> {

        val mutableLiveData = MutableLiveData<List<RepoGitHubModel>>()

        context.showProgressBar()

        api.listReposLivrData(userName).enqueue(object : Callback<MutableList<RepoGitHubModel>> {
            override fun onFailure(call: Call<MutableList<RepoGitHubModel>>, t: Throwable) {
                hideProgressBar()
                val error = "error"+t.localizedMessage
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<MutableList<RepoGitHubModel>>,
                response: Response<MutableList<RepoGitHubModel>>
            ) {
                hideProgressBar()
                val usersResponse = response.body()
                usersResponse?.let { mutableLiveData.value = it }
            }

        })

        return mutableLiveData
    }
}
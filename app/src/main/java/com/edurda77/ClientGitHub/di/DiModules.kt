package com.edurda77.ClientGitHub.di

import com.edurda77.ClientGitHub.domain.GitHubRepoApi
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import com.edurda77.ClientGitHub.model.BASE_URL
import com.edurda77.ClientGitHub.model.RetrofitGitHubUseCaseImpl
import com.edurda77.ClientGitHub.model.UserModel
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<GitHubRepoApi> {
        get<Retrofit>().create(GitHubRepoApi::class.java)
    }
    single<GitHubRepoUseCase> {
        RetrofitGitHubUseCaseImpl(get())
    }
    single< List<UserModel>>{
        val userOfGitHub = emptyList<UserModel>().toMutableList()
        userOfGitHub.add(UserModel("HayarovEd"))
        userOfGitHub.add(UserModel("kshalnov"))
        userOfGitHub.add(UserModel("robertBadamshin"))
        userOfGitHub
    }
    //factory { MySimplePresenter(get()) }
}


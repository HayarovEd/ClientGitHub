/*
package com.edurda77.ClientGitHub.di

import com.edurda77.ClientGitHub.domain.GitHubRepoApi
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import com.edurda77.ClientGitHub.model.BASE_URL
import com.edurda77.ClientGitHub.model.RetrofitGitHubUseCaseImpl
import com.edurda77.ClientGitHub.model.UserModel
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MyModule {

    @Provides
    fun getUsers(): List<UserModel> {
        val userOfGitHub = emptyList<UserModel>().toMutableList()
        userOfGitHub.add(UserModel("HayarovEd"))
        userOfGitHub.add(UserModel("kshalnov"))
        userOfGitHub.add(UserModel("robertBadamshin"))
        return userOfGitHub
    }
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun getApi(retrofit:Retrofit): GitHubRepoApi{
        return  retrofit.create(GitHubRepoApi::class.java)
    }
    @Provides
    fun getGitHubRepoUseCase(api:GitHubRepoApi): GitHubRepoUseCase {
        return RetrofitGitHubUseCaseImpl(api)
    }

}*/

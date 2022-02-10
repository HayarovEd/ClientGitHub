package com.edurda77.ClientGitHub.ui

import android.app.Application
import android.content.Context
import com.edurda77.ClientGitHub.domain.GitHubRepoUseCase
import com.edurda77.ClientGitHub.model.RetrofitGitHubUseCaseImpl

class App : Application() {
    val gitHubRepoUseCase: GitHubRepoUseCase by lazy { RetrofitGitHubUseCaseImpl() }
}
val Context.app
    get() = applicationContext as App
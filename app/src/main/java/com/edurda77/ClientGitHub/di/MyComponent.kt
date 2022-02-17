package com.edurda77.ClientGitHub.di

import com.edurda77.ClientGitHub.ui.MainActivity
import com.edurda77.ClientGitHub.ui.ReposGitHubActivity
import dagger.Component

@Component(modules = [MyModule::class])
interface MyComponent {
    fun inject(myActivity: MainActivity)
    fun injectGitHubActivity(reposGitHubActivity: ReposGitHubActivity) {

    }
}
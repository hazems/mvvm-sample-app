package com.example.test.mvvmsampleapp.di

import android.arch.lifecycle.ViewModelProvider
import com.example.test.mvvmsampleapp.service.repository.GitHubService
import com.example.test.mvvmsampleapp.viewmodel.ProjectViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(subcomponents = [(ViewModelSubComponent::class)])
internal class AppModule {

    @Singleton
    @Provides
    fun provideGithubService(): GitHubService {
        return Retrofit.Builder()
                .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(
            viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {

        return ProjectViewModelFactory(viewModelSubComponent.build())
    }
}

package com.example.test.mvvmsampleapp.service.repository

import com.example.test.mvvmsampleapp.service.model.Project

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Network service with a list of all network calls in the app via retrofit.
 */
interface GitHubService {

    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>

    companion object {
        val HTTPS_API_GITHUB_URL = "https://api.github.com/"
    }

}

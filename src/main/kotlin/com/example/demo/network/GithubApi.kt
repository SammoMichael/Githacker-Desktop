package com.example.demo.network

import com.example.demo.data.Repo
import retrofit2.Call
import retrofit2.http.GET


interface GithubApi {

    @GET("repositories")
    fun listRepos(): Call<List<Repo>>
}
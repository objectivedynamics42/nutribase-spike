package com.objectivedynamics.nutribase.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun createGithubApiService() : GitHubApiService{
    //TODO this is where I need to change the code to handle the specifics of my own query
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(GitHubApiService::class.java)
}
interface GitHubApiService {
    //TODO this is where I need to change the code to handle the specifics of my own query
    @GET("/search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("sort")sort: String = "stars",
        @Query("order")order: String = "desc"
    ) : Call<SearchResult>
}
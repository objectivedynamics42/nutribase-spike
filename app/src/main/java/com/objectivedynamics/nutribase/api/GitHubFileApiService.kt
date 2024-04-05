package com.objectivedynamics.nutribase.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

fun createGitHubApiFileService() : GitHubFileApiService{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(GitHubFileApiService::class.java)
}
interface GitHubFileApiService {
    @GET("/repos/{owner}/{repo}/contents/{path}")
    fun getFile(
        @Path("owner") owner:String,
        @Path("repo") repo:String,
        @Path("path") path:String
    ) : Call<FileResult>
}

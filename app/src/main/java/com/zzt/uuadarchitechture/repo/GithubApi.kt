package com.zzt.uuadarchitechture.repo

import androidx.lifecycle.LiveData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Android_ZzT on 2019-12-22.
 */

interface GithubApi {
	@GET("/users/{name}/repos")
	fun getUserRepos(@Path("name") name: String): LiveData<Response<List<GithubRepo>>>
}
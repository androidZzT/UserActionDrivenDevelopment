package com.zzt.uuadarchitechture.repo

import com.google.gson.Gson
import com.zzt.infra_lib.data_streaming.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Android_ZzT on 2019-12-22.
 */

val retrofit: Retrofit =
	Retrofit.Builder()
		.baseUrl("https://api.github.com")
		.addCallAdapterFactory(LiveDataCallAdapterFactory.create())
		.addConverterFactory(GsonConverterFactory.create())
		.build()

fun githubService(): GithubApi {
	return retrofit.create(GithubApi::class.java)
}
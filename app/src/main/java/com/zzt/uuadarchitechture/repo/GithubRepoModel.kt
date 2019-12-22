package com.zzt.uuadarchitechture.repo

import com.google.gson.annotations.SerializedName

/**
 * Created by Android_ZzT on 2019-12-22.
 */
data class GithubRepo(
	@SerializedName("id") val id: Long,
	@SerializedName("name") val name: String,
	@SerializedName("url") val repoUrl: String,
	@SerializedName("stargazers_count") val starCount: Int,
	@SerializedName("owner") val owner:Owner
)

data class Owner(
	@SerializedName("id") val userId: Long,
	@SerializedName("url") val homeUrl: String,
	@SerializedName("avatar_url") val avatarUrl: String
)
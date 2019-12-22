package com.zzt.uuadarchitechture.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.zzt.infra_lib.data_streaming.StatefulData
import com.zzt.uuadarchitechture.repo.GithubRepo
import com.zzt.uuadarchitechture.repo.githubService
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.CoroutineContext

/**
 * Created by Android_ZzT on 2019-12-22.
 */

class GithubRepoViewModel : ViewModel() {

	fun getUserReposInfo(): LiveData<StatefulData<List<GithubRepo>?>> = liveData {
		emit(StatefulData.loading("loading..."))
		try {
			val response: Response<List<GithubRepo>>? = githubService().getUserRepos("androidzzt").value
			if (response != null) {
				if (response.isSuccessful) {
					emit(StatefulData.success(response.body()))
				} else {
					emit(StatefulData.failed(response.message()))
				}
			}
		} catch (e : IOException) {
			emit(StatefulData.failed(e.message))
		}
	}
}
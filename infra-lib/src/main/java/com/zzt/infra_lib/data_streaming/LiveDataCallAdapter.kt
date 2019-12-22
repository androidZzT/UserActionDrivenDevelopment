package com.zzt.infra_lib.data_streaming

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Android_ZzT on 2019-12-22.
 */

private const val TAG = "LiveDataCallAdapter"

class LiveDataCallAdapter<R>(private val responseType: Type) :
	CallAdapter<R, LiveData<Response<R>>> {

	override fun adapt(call: Call<R>): LiveData<Response<R>> {
		return object : LiveData<Response<R>>() {
			var started = AtomicBoolean(false)

			override fun onActive() {
				super.onActive()
				if (started.compareAndSet(false, true)) {
					call.enqueue(object : Callback<R> {
						override fun onFailure(call: Call<R>, t: Throwable) {
							Log.d(TAG, "onFailure errMsg= ${t.message}")
							throw IOException(t)
						}

						override fun onResponse(call: Call<R>, response: Response<R>) {
							Log.d(TAG, "onResponse")
							postValue(response)
						}
					})
				}
			}
		}
	}

	override fun responseType(): Type {
		return responseType
	}
}
package com.zzt.infra_lib.data_streaming

import androidx.lifecycle.LiveData
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by Android_ZzT on 2019-12-22.
 */

class LiveDataCallAdapterFactory : CallAdapter.Factory {

	companion object {
		fun create(): LiveDataCallAdapterFactory {
			return LiveDataCallAdapterFactory()
		}
	}

	private constructor()

	override fun get(
		returnType: Type,
		annotations: Array<Annotation>,
		retrofit: Retrofit
	): CallAdapter<*, *>? {
		if (getRawType(returnType) != LiveData::class.java) {
			return null
		}
		val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
		val rawObservableType = getRawType(observableType)
		if (rawObservableType != Response::class.java)
			throw IllegalArgumentException("Illegal argument Type")
		if (observableType !is ParameterizedType)
			throw IllegalArgumentException("resource must be parameterized")

		val bodyType = getParameterUpperBound(0, observableType as ParameterizedType)
		return LiveDataCallAdapter<Any>(bodyType)
	}
}
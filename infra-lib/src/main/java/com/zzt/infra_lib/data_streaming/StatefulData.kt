package com.zzt.infra_lib.data_streaming

/**
 * Created by Android_ZzT on 2019-12-22.
 */

class StatefulData<T> {

	private constructor(state: DataState, msg: String?, data: T?) {
		mState = state
		mExtraMsg = msg
		mData = data
	}

	companion object {
		fun <T> success(data: T): StatefulData<T> =
			StatefulData(DataState.SUCCESS, null, data)

		fun <T> failed(errorMsg: String?): StatefulData<T> =
			StatefulData(DataState.FAILED, errorMsg, null)

		fun <T> loading(msg: String?): StatefulData<T> =
			StatefulData(DataState.LOADING, msg, null)
	}

	private var mExtraMsg: String? = null

	private var mState: DataState

	private var mData: T? = null

	enum class DataState {
		LOADING,
		SUCCESS,
		FAILED
	}
}
package com.app.bookstore.base.networkstate.errorhandling

import com.app.bookstore.base.networkstate.*
import kotlinx.coroutines.flow.flow


/**
 * Created by Nalan Ulusoy on 07,Temmuz,2022
 */
inline fun <REMOTE> networkResource(
    crossinline fetchFromRemote: suspend () -> ApiResponse<REMOTE>,
    crossinline onFetchFailed: (errorBody: String?, statusCode: Int) -> Unit = { _: String?, _: Int -> }
) = flow<Resource<REMOTE>> {
    emit(Resource.loading(null))
    fetchFromRemote()?.let { apiResponse ->
        when (apiResponse) {
            is ApiSuccessResponse -> {
                apiResponse.body?.let {
                    emit(Resource.success(data = it))
                }
            }
            is ApiErrorResponse -> {
                onFetchFailed(apiResponse.errorMessage, apiResponse.statusCode)
                emit(Resource.error(msg = apiResponse.errorMessage, statusCode = apiResponse.statusCode, data = null))
            }
            is ApiEmptyResponse -> {
                emit(Resource.success(data = null))
            }
            else -> {}
        }
    }
}
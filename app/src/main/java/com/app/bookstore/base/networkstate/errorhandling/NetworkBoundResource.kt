package com.app.bookstore.base.networkstate.errorhandling

import com.app.bookstore.base.networkstate.*
import kotlinx.coroutines.flow.*


/**
 * Created by Nalan Ulusoy on 07,Temmuz,2022
 */
inline fun <DB, REMOTE> networkBoundResource(
    crossinline fetchFromLocal: suspend () -> Flow<DB>?,
    crossinline shouldFetchFromRemote: suspend (DB?) -> Boolean = { true },
    crossinline fetchFromRemote: suspend () -> Flow<ApiResponse<REMOTE>>,
    crossinline processRemoteResponse: (response: ApiSuccessResponse<REMOTE>) -> Unit = { },
    crossinline saveRemoteData: suspend (REMOTE) -> Unit = { },
    crossinline onFetchFailed: (errorBody: String?, statusCode: Int) -> Unit = { _: String?, _: Int -> }
) = flow<Resource<DB>> {
    emit(Resource.loading(null))
    val localData = fetchFromLocal()?.first()

    if (shouldFetchFromRemote(localData)) {
        emit(Resource.loading(localData))
        fetchFromRemote().collect { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    processRemoteResponse(apiResponse)
                    apiResponse.body?.let { saveRemoteData(it) }
                    fetchFromLocal()?.let {
                        emitAll(
                            it.map { dbData ->
                                Resource.success(data = dbData)
                            }
                        )
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed(apiResponse.errorMessage, apiResponse.statusCode)
                    fetchFromLocal()?.let {
                        emitAll(
                            it.map { dbData ->
                                Resource.error(
                                    msg = apiResponse.errorMessage,
                                    statusCode = apiResponse.statusCode,
                                    data = dbData
                                )
                            }
                        )
                    }
                }
                is ApiEmptyResponse -> {
                    emit(Resource.success(data = null))
                }
                else -> {}
            }
        }
    } else {
        fetchFromLocal()?.let { emitAll(it.map { Resource.success(data = it) }) }
    }
}
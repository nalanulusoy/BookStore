package com.app.bookstore.feature.detail.data.datasource

import com.app.bookstore.base.networkstate.Resource
import com.app.bookstore.base.networkstate.errorhandling.networkResource
import com.app.bookstore.feature.detail.data.VolumeDetailApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class DetailRemoteDataSource constructor(private val apiService: VolumeDetailApiService) {

    fun getVolumeService(params: Params) =
        networkResource(fetchFromRemote = { apiService.getVolumeDetail(params.volumeId) },
            onFetchFailed = { errorBody, statusCode -> }).map {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Resource.loading(null)
                }
                Resource.Status.SUCCESS -> {
                    val quote = it.data
                    Resource.success(quote)
                }
                is Resource.Status.ERROR -> {
                    val error = it.status as Resource.Status.ERROR
                    Resource.error(error.message, error.statusCode, it.data)
                }
            }
        }.flowOn(Dispatchers.IO)

    data class Params(val volumeId: String? = null)
}
package com.app.bookstore.feature.detail.domain


import androidx.annotation.WorkerThread
import com.app.bookstore.feature.detail.data.VolumeDetailApiService
import com.app.bookstore.feature.detail.data.VolumeDetailResponse
import kotlinx.coroutines.flow.flow

/**
 * Created by Nalan Ulusoy on 04,Temmuz,2022
 */
class VolumeDetailRepository(private val apiService: VolumeDetailApiService) {

 suspend  fun getVolumeService(params:Params) =  flow {
      val response = apiService.getVolumeDetail(params.volumeId)
        emit(response)
    }

    data class Params(val volumeId:String?= null)
}
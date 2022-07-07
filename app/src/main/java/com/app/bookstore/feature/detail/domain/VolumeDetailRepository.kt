package com.app.bookstore.feature.detail.domain


import com.app.bookstore.feature.detail.data.VolumeDetailApiService
import kotlinx.coroutines.flow.flow

/**
 * Created by Nalan Ulusoy on 04,Temmuz,2022
 */
class VolumeDetailRepository(private val apiService: VolumeDetailApiService,) {

 suspend  fun getVolumeService(params:Params) =  flow {
      val response = apiService.getVolumeDetail(params.volumeId)
        if(response.isSuccessful){
            emit(response)
        }
      else{
        //Todo error handling
        }
    }

    data class Params(val volumeId:String?= null)
}
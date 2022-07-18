package com.app.bookstore.feature.detail.domain


import com.app.bookstore.base.networkstate.Resource
import com.app.bookstore.feature.detail.data.response.VolumeDetailResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Nalan Ulusoy on 04,Temmuz,2022
 */
interface VolumeDetailRepository {

    fun getVolumeService(volumeId: String): Flow<Resource<VolumeDetailResponse>>
}
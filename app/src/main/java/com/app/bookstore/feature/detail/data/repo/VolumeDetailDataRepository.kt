package com.app.bookstore.feature.detail.data.repo

import com.app.bookstore.base.networkstate.Resource
import com.app.bookstore.feature.detail.data.datasource.DetailRemoteDataSource
import com.app.bookstore.feature.detail.data.response.VolumeDetailResponse
import com.app.bookstore.feature.detail.domain.VolumeDetailRepository
import kotlinx.coroutines.flow.Flow


/**
 * Created by Nalan Ulusoy on 18,Temmuz,2022
 */
class VolumeDetailDataRepository(private val detailRemoteDataSource: DetailRemoteDataSource) :
    VolumeDetailRepository {

    override fun getVolumeService(volumeId: String): Flow<Resource<VolumeDetailResponse>> =
        detailRemoteDataSource.getVolumeService(DetailRemoteDataSource.Params(volumeId))

}
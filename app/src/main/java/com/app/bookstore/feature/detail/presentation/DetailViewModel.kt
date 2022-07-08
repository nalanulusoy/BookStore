package com.app.bookstore.feature.detail.presentation

import com.app.bookstore.base.BaseViewModel
import com.app.bookstore.base.networkstate.Resource
import com.app.bookstore.feature.detail.data.VolumeDetailResponse
import com.app.bookstore.feature.detail.domain.VolumeDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by nalanulusoy on 11,Mart,2022
 */
@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: VolumeDetailRepository) :
    BaseViewModel() {

    private val volumeId: MutableStateFlow<String> = MutableStateFlow("")
    val detailData: Flow<Resource<VolumeDetailResponse>> =
        volumeId.flatMapLatest { repository.getVolumeService(VolumeDetailRepository.Params(it)) }

    fun fetchVolumeIdById(id: String) = volumeId.tryEmit(id)
}
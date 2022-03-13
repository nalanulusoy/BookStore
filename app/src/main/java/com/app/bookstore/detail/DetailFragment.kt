package com.app.bookstore.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.bookstore.R
import com.app.bookstore.base.BaseFragment
import com.app.bookstore.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by nalanulusoy on 11,Mart,2022
 */
@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel,DetailFragmentBinding >()  {

    private val viewModel: DetailViewModel by viewModels()
    companion object {
        fun newInstance() = DetailFragment()
        const val LAYOUT_RES_ID = R.layout.dashboard_fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun provideLayoutResId() = LAYOUT_RES_ID

    override fun provideViewModel() = viewModel

    override fun bindViewModel(binding: DetailFragmentBinding) {
        binding.viewmodel = viewModel
    }

}
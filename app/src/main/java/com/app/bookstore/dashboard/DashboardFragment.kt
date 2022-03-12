package com.app.bookstore.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.app.bookstore.R
import com.app.bookstore.base.BaseFragment
import com.app.bookstore.databinding.DashboardFragmentBinding
import com.app.bookstore.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardViewModel, DashboardFragmentBinding >() {

    private val viewModel: DashboardViewModel by viewModels()

    companion object {
        const val LAYOUT_RES_ID = R.layout.dashboard_fragment
        fun newInstance() = DashboardFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getBinding().detailButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.dashboardActionScreen);
        }
    }

    override fun provideLayoutResId() = LAYOUT_RES_ID

    override fun provideViewModel() = viewModel

    override fun bindViewModel(binding: DashboardFragmentBinding) {
        binding.viewmodel = viewModel
    }

}
package com.app.bookstore.dashboard.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.app.bookstore.R
import com.app.bookstore.base.BaseFragment
import com.app.bookstore.databinding.DashboardFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardViewModel, DashboardFragmentBinding >() {

    private val viewModel: DashboardViewModel by viewModels()

    companion object {
        const val LAYOUT_RES_ID = R.layout.dashboard_fragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       /* getBinding().detailButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.dashboardActionScreen);
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
    }

    override fun provideLayoutResId() = LAYOUT_RES_ID

    override fun provideViewModel() = viewModel

    override fun bindViewModel(binding: DashboardFragmentBinding) {
        binding.viewmodel = viewModel
    }

}
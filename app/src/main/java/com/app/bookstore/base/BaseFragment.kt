package com.app.bookstore.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


/**
 * Created by nalanulusoy on 11,Mart,2022
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {
    private lateinit var binding: DB
    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, provideLayoutResId(), container, false)
        bindViewModel(binding)
        return binding.root
    }

    open fun getViewModel() = viewModel

    open fun getBinding() = binding

    abstract fun provideLayoutResId(): Int

    abstract fun provideViewModel(): VM

    abstract fun bindViewModel(binding: DB)
}
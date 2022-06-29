package com.app.bookstore.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by nalanulusoy on 11,Mart,2022
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : ComponentActivity() {

    private lateinit var binding: DB
    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, provideLayoutResId())
        viewModel = provideViewModel()
        bindViewModel(binding)

    }

    open fun getBinding() = binding

    abstract fun provideLayoutResId(): Int

    abstract fun provideViewModel(): VM

    abstract fun bindViewModel(binding: DB)

}
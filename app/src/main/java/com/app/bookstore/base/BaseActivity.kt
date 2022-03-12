package com.app.bookstore.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by nalanulusoy on 11,Mart,2022
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

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
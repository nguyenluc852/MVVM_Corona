package com.s.basemvvm.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.s.basemvvm.BR
import com.s.basemvvm.R
import com.s.basemvvm.base.BaseFragment
import com.s.basemvvm.databinding.MainFragmentBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : BaseFragment<MainFragmentViewModel, MainFragmentBinding>(R.layout.main_fragment) {


    override fun brVariableId(): Int = BR.mainFragmentViewModel

    override fun initView(binding: MainFragmentBinding?) {

    }

    override fun observeData() {
        viewModel.totalResult.observe {
            binding?.lblTotalCases?.text = it?.totalCases
            binding?.lblTotalDeath?.text = it?.totalDeaths
            binding?.lblTotalRecover?.text = it?.totalRecovered
        }
    }
}
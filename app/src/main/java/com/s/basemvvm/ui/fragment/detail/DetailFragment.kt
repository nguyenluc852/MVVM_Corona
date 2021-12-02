package com.s.basemvvm.ui.fragment.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.s.basemvvm.BR
import com.s.basemvvm.R
import com.s.basemvvm.base.BaseFragment
import com.s.basemvvm.databinding.FragmentDetailBinding
import com.s.basemvvm.model.Result
import com.s.basemvvm.utils.AppConstant


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : BaseFragment<DetailFragmentViewModel, FragmentDetailBinding>(R.layout.fragment_detail) {


    override fun brVariableId(): Int = BR.detailViewModel

    override fun initView(binding: FragmentDetailBinding?) {
        val detail = viewModel.args.getSerializable(AppConstant.INFO_DETAIL_KEY_BUNDLE) as  Result
        viewModel.setInfoDetail(detail)
    }


}
package com.s.basemvvm.ui.fragment.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.s.basemvvm.BR
import com.s.basemvvm.R
import com.s.basemvvm.base.BaseAdapter
import com.s.basemvvm.base.BaseFragment
import com.s.basemvvm.databinding.MainFragmentBinding
import com.s.basemvvm.model.Result
import com.s.basemvvm.utils.AppConstant
import com.s.basemvvm.utils.Logger
import com.s.basemvvm.utils.addItemDividers
import com.s.basemvvm.utils.getStatusBarHeight


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : BaseFragment<MainFragmentViewModel, MainFragmentBinding>(R.layout.main_fragment), BaseAdapter.DefaultClickListener{

    override fun brVariableId(): Int = BR.mainFragmentViewModel

    private val adapter by lazy { ListCountriesAdapter(this) }
    override fun isFragmentScopeViewModel(): Boolean = false

    override fun initView(binding: MainFragmentBinding?) {
        binding?.root.addMarginTopEqualStatusBarHeight()
        binding?.rcvListCountry?.addItemDividers()
        binding?.rcvListCountry?.adapter = adapter
    }

    override fun observeData() {
        viewModel.totalResult.observe {
            binding?.lblTotalCases?.text = it?.totalCases
            binding?.lblTotalDeath?.text = it?.totalDeaths
            binding?.lblTotalRecover?.text = it?.totalRecovered
        }

        viewModel.listCountriesData.observe {
            adapter.submitList(it)
        }


    }

    fun View?.addMarginTopEqualStatusBarHeight() {
        if (this == null) return
        setTag("TAG_OFFSET")
        val keyOffset = -123
        val haveSetOffset: Any? = getTag(keyOffset)
        if (haveSetOffset != null && haveSetOffset as Boolean) return
        val layoutParams = layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(
            layoutParams.leftMargin,
            layoutParams.topMargin + context.getStatusBarHeight(),
            layoutParams.rightMargin,
            layoutParams.bottomMargin
        )
        setTag(keyOffset, true)
    }

    override fun <T> onItemClick(position: Int, item: T) {
        val bundle = Bundle()
        bundle.putSerializable(AppConstant.INFO_DETAIL_KEY_BUNDLE, item as Result)
        findNavController().navigate(R.id.action_nav_main_fragment_to_detailFragment, bundle)
    }


}
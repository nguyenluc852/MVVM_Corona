package com.s.basemvvm.ui.fragment.main


import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.s.basemvvm.BR
import com.s.basemvvm.R
import com.s.basemvvm.base.BaseFragment
import com.s.basemvvm.databinding.MainFragmentBinding
import com.s.basemvvm.utils.addItemDividers
import com.s.basemvvm.utils.getStatusBarHeight


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : BaseFragment<MainFragmentViewModel, MainFragmentBinding>(R.layout.main_fragment){

    override fun brVariableId(): Int = BR.mainFragmentViewModel

    private var adapter : ListCountriesAdapter? = null
    override fun isFragmentScopeViewModel(): Boolean = false

    override fun initView(binding: MainFragmentBinding?) {
        binding?.root.addMarginTopEqualStatusBarHeight()

        adapter = ListCountriesAdapter()
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
            adapter?.submitList(it)
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



}
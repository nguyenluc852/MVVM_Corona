package com.s.basemvvm.ui.fragment.main


import com.s.basemvvm.BR
import com.s.basemvvm.R
import com.s.basemvvm.base.BaseAdapter
import com.s.basemvvm.model.Result
import com.s.basemvvm.utils.Logger

class ListCountriesAdapter(listener: DefaultClickListener) :  BaseAdapter<Result>(R.layout.item_countries,  listener ) {
    override fun brVariableId(): Int = BR.item_country
}
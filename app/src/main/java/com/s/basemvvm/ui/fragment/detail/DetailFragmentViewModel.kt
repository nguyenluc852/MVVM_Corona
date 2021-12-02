package com.s.basemvvm.ui.fragment.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.s.basemvvm.base.BaseViewModel
import com.s.basemvvm.model.Result

class DetailFragmentViewModel(app:  Application): BaseViewModel(app) {
    var infoDetail =  MutableLiveData<Result>()
    fun setInfoDetail(info : Result) {
        infoDetail.value = info
    }
}
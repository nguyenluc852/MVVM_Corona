package com.s.basemvvm.ui.fragment.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.s.basemvvm.base.BaseViewModel
import com.s.basemvvm.base.onFailed
import com.s.basemvvm.base.onSucceed
import com.s.basemvvm.model.Result
import com.s.basemvvm.repo.network
import com.s.basemvvm.utils.Logger

class MainFragmentViewModel(app: Application):  BaseViewModel(app) {

    val totalResult = MutableLiveData<Result>()
    val listCountriesData = MutableLiveData<ArrayList<Result>>()

    init {
        getTotalData()
        getListCountriesData()
    }
    fun getTotalData() = coroutines {
        network().getTotalData().onSucceed {
            totalResult.postValue(it.result)
        }
            .onFailed { errCode, errBody -> Logger.d(errBody.toString())  }
    }

    fun  getListCountriesData() = coroutines {
        network().getCountriesData().onSucceed {
            if (it.result.isNullOrEmpty()) return@onSucceed
            listCountriesData.postValue(it.result)
        }
    }



}
package com.s.basemvvm.model

import com.s.basemvvm.base.Model

data class Result (
    var country: String,
    var totalCases: String,
    var newCases: String,
    var totalDeaths: String,
    var newDeaths: String,
    var totalRecovered: String,
    var activeCases: String
):Model() {
    override val uniqueId: Any
        get() = country
}


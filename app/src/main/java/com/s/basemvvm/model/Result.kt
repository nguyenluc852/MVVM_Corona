package com.s.basemvvm.model

import com.s.basemvvm.base.Model
import java.io.Serializable

data class Result (
    var country: String,
    var totalCases: String,
    var newCases: String,
    var totalDeaths: String,
    var newDeaths: String,
    var totalRecovered: String,
    var activeCases: String
):Model(), Serializable {
    override val uniqueId: Any
        get() = country
}


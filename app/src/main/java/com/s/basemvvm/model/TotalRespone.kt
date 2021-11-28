package com.s.basemvvm.model

data class TotalRespone(
    val success: Boolean,
    val result: Result
)


data class Result (
    var totalDeaths: String,
    var totalCases: String,
    var totalRecovered: String
)


package com.kunal.actmobile.data.network.models

import com.google.gson.annotations.SerializedName

data class CountryListResponse(
    @SerializedName("code")
    var resultCode:Int,
    @SerializedName("result")
    var countryList:List<Country>
)

package com.kunal.actmobile.data.network.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    var countryName:String,
    @SerializedName("code")
    var countryCode: String
)
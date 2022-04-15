package com.kunal.actmobile.data.network.apis

import com.kunal.actmobile.data.network.models.CountryListResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {

    @GET("countries")
    suspend fun getCountries():Response<CountryListResponse>

}
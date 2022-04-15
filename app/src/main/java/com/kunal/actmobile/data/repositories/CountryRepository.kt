package com.kunal.actmobile.data.repositories

import androidx.lifecycle.MutableLiveData
import com.kunal.actmobile.data.network.SafeApiRequest
import com.kunal.actmobile.data.network.apis.CountryApi
import com.kunal.actmobile.data.network.models.Country

class CountryRepository(
    private val api: CountryApi
) : SafeApiRequest() {

    private val countryList = MutableLiveData<List<Country>>()

    suspend fun fetchCountries(): MutableLiveData<List<Country>> {
        getCountries()
        return countryList
    }

    private suspend fun getCountries() {
        val response = apiRequest { api.getCountries() }
        countryList.postValue(response.countryList)
    }
}
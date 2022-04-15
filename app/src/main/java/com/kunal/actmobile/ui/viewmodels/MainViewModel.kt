package com.kunal.actmobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunal.actmobile.data.network.models.Country
import com.kunal.actmobile.data.repositories.CountryRepository
import com.kunal.actmobile.data.repositories.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val countryRepository: CountryRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {

    private var _countryList = MutableLiveData<List<Country>>()
    val countryList: LiveData<List<Country>> = _countryList

    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = countryRepository.fetchCountries()
            launch(Dispatchers.Main) {
                _countryList.value = response.value
            }
        }
    }

}
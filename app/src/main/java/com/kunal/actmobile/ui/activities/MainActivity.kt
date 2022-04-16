package com.kunal.actmobile.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import com.kunal.actmobile.R
import com.kunal.actmobile.data.datastore.DatastoreManager
import com.kunal.actmobile.databinding.ActivityMainBinding
import com.kunal.actmobile.ui.fragments.CountrySelectionBottomSheetDialog
import com.kunal.actmobile.ui.fragments.CountrySelectionBottomSheetDialog.Companion.TAG
import com.kunal.actmobile.ui.viewmodels.MainViewModel
import com.kunal.actmobile.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var datastoreManager: DatastoreManager

    private val viewModel: MainViewModel by viewModels()
    private lateinit var view: ActivityMainBinding

    companion object {
        const val BASE_IMAGE_URL = "https://countryflagsapi.com/png/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
        view.lifecycleOwner = this
        getData()
        initializeViews()
        initializeObservers()
    }

    private fun initializeObservers() {
        datastoreManager.countryName.asLiveData().observe(this) { countryName ->
            if (countryName.isNullOrBlank()) {
                view.countryName.text = resources.getString(R.string.country_name)
                return@observe
            }
            view.countryName.text = countryName
        }
        datastoreManager.countryCode.asLiveData().observe(this) { countryCode ->
            countryCode?.let { it -> view.countryFlag.loadImage("$BASE_IMAGE_URL${it}") }
        }
    }

    private fun initializeViews() {
        view.selectedCountry.setOnClickListener {
            val countrySelectionBottomSheetDialog = CountrySelectionBottomSheetDialog.newInstance()
            countrySelectionBottomSheetDialog.show(supportFragmentManager, TAG)
        }
    }

    private fun getData() {
        viewModel.getCountries()
    }
}
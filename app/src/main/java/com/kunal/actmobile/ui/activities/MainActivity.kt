package com.kunal.actmobile.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
        view.lifecycleOwner = this
        getData()
        initializeViews()
    }

    private fun initializeViews() {
        view.countryFlag.loadImage("https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1350px-Flag_of_India.svg.png")
        view.selectedCountry.setOnClickListener {
            val countrySelectionBottomSheetDialog = CountrySelectionBottomSheetDialog.newInstance()
            countrySelectionBottomSheetDialog.show(supportFragmentManager,TAG)
        }
    }

    private fun getData() {
        viewModel.getCountries()
    }
}
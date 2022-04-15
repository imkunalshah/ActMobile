package com.kunal.actmobile.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.kunal.actmobile.R
import com.kunal.actmobile.databinding.ActivityMainBinding
import com.kunal.actmobile.ui.fragments.CountrySelectionBottomSheetDialog
import com.kunal.actmobile.ui.fragments.CountrySelectionBottomSheetDialog.Companion.TAG
import com.kunal.actmobile.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
        val countrySelectionBottomSheetDialog = CountrySelectionBottomSheetDialog.newInstance()
        countrySelectionBottomSheetDialog.show(supportFragmentManager,TAG)
    }

    private fun getData() {
        viewModel.getCountries()
    }
}
package com.kunal.actmobile.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment
import com.kunal.actmobile.data.datastore.DatastoreManager
import com.kunal.actmobile.data.network.models.Country
import com.kunal.actmobile.databinding.DialogCategorySelectionBinding
import com.kunal.actmobile.ui.adapters.CountryListAdapter
import com.kunal.actmobile.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class CountrySelectionBottomSheetDialog : RoundedBottomSheetDialogFragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var view: DialogCategorySelectionBinding

    @Inject
    lateinit var datastoreManager: DatastoreManager

    private val _countryListAdapter by lazy {
        CountryListAdapter(
            null,
            emptyList(),
            ::onCountrySelected
        )
    }

    private fun onCountrySelected(selectedCountry: Country) {
        lifecycleScope.launch(Dispatchers.IO) {
            datastoreManager.saveCountryName(selectedCountry.countryName)
            datastoreManager.saveCountryCode(selectedCountry.countryCode)
            dismiss()
        }
    }

    private var countryListAdapter: CountryListAdapter? = null
        get() {
            kotlin.runCatching {
                field = _countryListAdapter
            }.onFailure {
                Timber.d("Error: $it")
                field = null
            }
            return field
        }

    companion object {
        const val TAG = "CountrySelectionBottomSheetDialog"

        fun newInstance(): CountrySelectionBottomSheetDialog {
            return CountrySelectionBottomSheetDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = DialogCategorySelectionBinding.inflate(inflater, container, false)
        view.lifecycleOwner = viewLifecycleOwner
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        initializeObservers()
    }

    private fun initializeViews() {
        view.countriesListRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = countryListAdapter
        }
    }

    private fun initializeObservers() {
        datastoreManager.countryName.asLiveData().observe(viewLifecycleOwner) { selectedCountry ->
            countryListAdapter?.updateSelectedCountry(selectedCountry ?: "Region Name")
        }
        viewModel.countryList.observe(viewLifecycleOwner) { countryList ->
            if (!countryList.isNullOrEmpty()) {
                Timber.tag("countryList").d(countryList.toString())
                countryListAdapter?.updateCountryList(countryList)
            } else {
                Timber.tag("countryList").d("Empty")
            }
        }
    }

}
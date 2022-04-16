package com.kunal.actmobile.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kunal.actmobile.data.network.models.Country
import com.kunal.actmobile.databinding.LayoutCountryCardBinding
import com.kunal.actmobile.ui.activities.MainActivity.Companion.BASE_IMAGE_URL
import com.kunal.actmobile.utils.loadImage
import timber.log.Timber

class CountryListAdapter(
    selectedCountry: String?,
    countryList: List<Country>,
    val onCountrySelected: (selectedCountry: Country) -> Unit
) : RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    private var _countryList: MutableList<Country>? = null
    private var _selectedCountry: String? = null

    init {
        _selectedCountry = selectedCountry
        _countryList = countryList.toMutableList()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.CountryListViewHolder {
        val view =
            LayoutCountryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListAdapter.CountryListViewHolder, position: Int) {
        val country = _countryList?.get(position)
        country?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = _countryList?.size ?: 0

    fun updateSelectedCountry(selectedCountry: String) {
        _selectedCountry = selectedCountry
    }

    fun updateCountryList(countryList: List<Country>) {
        _countryList?.addAll(countryList.toMutableList())
        notifyDataSetChanged()
    }

    inner class CountryListViewHolder(private val view: LayoutCountryCardBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(country: Country) {
            Timber.d("ImageUrl: $BASE_IMAGE_URL${country.countryCode}")
            view.countryFlag.loadImage("$BASE_IMAGE_URL${country.countryCode}")
            view.countryName.text = country.countryName
            if (country.countryName.equals(_selectedCountry, true)) {
                view.selectedBackground.visibility = View.VISIBLE
                view.unSelectedBackground.visibility = View.GONE
            } else {
                view.selectedBackground.visibility = View.GONE
                view.unSelectedBackground.visibility = View.VISIBLE
            }
            view.radioButton.setOnClickListener {
                onCountrySelected.invoke(country)
            }
        }
    }
}
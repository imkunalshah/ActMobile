package com.kunal.actmobile.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kunal.actmobile.databinding.DialogCategorySelectionBinding
import com.kunal.actmobile.ui.viewmodels.MainViewModel
import timber.log.Timber

class CountrySelectionBottomSheetDialog : RoundedBottomSheetDialogFragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var view: DialogCategorySelectionBinding

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

    }

    private fun initializeObservers() {
        viewModel.countryList.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                Timber.tag("countryList").d(it.toString())
            } else {
                Timber.tag("countryList").d("Empty")
            }
        }
    }

}
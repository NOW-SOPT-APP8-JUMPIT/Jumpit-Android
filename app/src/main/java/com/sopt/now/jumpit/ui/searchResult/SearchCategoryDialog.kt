package com.sopt.now.jumpit.ui.searchResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEachIndexed
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.sopt.now.jumpit.databinding.DialogSearchCategoryBinding

class SearchCategoryDialog : BottomSheetDialogFragment() {
    private var _binding: DialogSearchCategoryBinding? = null
    private val binding: DialogSearchCategoryBinding get() = requireNotNull(_binding)
    private val viewModel: SearchResultViewModel by activityViewModels<SearchResultViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogSearchCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDialogBehavior()
        setupSetCategoriesClickListener()
    }

    private fun setupDialogBehavior() {
        val bottomSheet =
            dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
        }
    }

    private fun setupSetCategoriesClickListener() {
        binding.tvSearchBtn.setOnClickListener {
            setCheckedCategories()
            dismiss()
        }
    }

    private fun setCheckedCategories() {
        val checkedCategories = mutableListOf<Int>()
        binding.cgSearchCategory.forEachIndexed { index, view ->
            if (view is Chip && view.isChecked) checkedCategories.add(index + SERVER_CATEGORY_OFFSET)
        }
        viewModel.setCheckedCategories(checkedCategories)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val SERVER_CATEGORY_OFFSET = 1
    }
}

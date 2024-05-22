package com.sopt.now.jumpit.ui.searchResult

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.DialogSearchCategoryBinding

class SearchCategoryDialog : DialogFragment() {
    private var _binding: DialogSearchCategoryBinding? = null
    private val binding: DialogSearchCategoryBinding get() = requireNotNull(_binding)

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
        setupDialogAppearance()
    }

    private fun setupDialogAppearance() {
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes?.windowAnimations = R.style.BottomSheetDialogAnimation
            setGravity(Gravity.BOTTOM)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

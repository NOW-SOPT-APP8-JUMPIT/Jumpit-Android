package com.sopt.now.jumpit.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopt.now.jumpit.databinding.FragmentAddBottomSheetDialogBinding

class AddBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentAddBottomSheetDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBtnAddCloseClick()
        onTextResumeAddClick()
    }

    private fun onBtnAddCloseClick() {
        binding.btnAddClose.setOnClickListener {
            dismiss()
        }
    }

    private fun onTextResumeAddClick() {
        binding.tvAddResume.setOnClickListener {
            dismiss()
        }
    }
}
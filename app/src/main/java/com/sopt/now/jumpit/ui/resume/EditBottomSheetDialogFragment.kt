package com.sopt.now.jumpit.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopt.now.jumpit.databinding.FragmentEditBottomSheetDialogBinding

class EditBottomSheetDialogFragment() : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentEditBottomSheetDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBtnEditCloseClick()
        onTextResumeDeleteClick()
    }

    private fun onBtnEditCloseClick() {
        binding.btnEditClose.setOnClickListener {
            dismiss()
        }
    }

    private fun onTextResumeDeleteClick() {
        binding.tvDeleteResume.setOnClickListener {
            // TODO: 이력서 삭제 기능 구현
        }
    }
}
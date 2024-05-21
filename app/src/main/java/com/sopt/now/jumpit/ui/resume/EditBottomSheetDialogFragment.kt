package com.sopt.now.jumpit.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopt.now.jumpit.databinding.FragmentEditBottomSheetDialogBinding

class EditBottomSheetDialogFragment(private val position: Int) : BottomSheetDialogFragment() {
    private var _binding: FragmentEditBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<ResumeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBtnEditCloseClick()
        onTextResumeDeleteClick()
    }

    private fun onBtnEditCloseClick() {
        binding.ivCloseEditDialog.setOnClickListener {
            dismiss()
        }
    }

    private fun onTextResumeDeleteClick() {
        binding.tvDeleteResume.setOnClickListener {
            viewModel.deleteResume(position)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
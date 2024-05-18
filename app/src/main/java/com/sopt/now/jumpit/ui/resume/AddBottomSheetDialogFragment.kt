package com.sopt.now.jumpit.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopt.now.jumpit.databinding.FragmentAddBottomSheetDialogBinding

class AddBottomSheetDialogFragment() : BottomSheetDialogFragment() {
    private var _binding: FragmentAddBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<ResumeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBtnAddCloseClick()
        onTextResumeAddClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBtnAddCloseClick() {
        binding.btnAddClose.setOnClickListener {
            dismiss()
        }
    }

    private fun onTextResumeAddClick() {
        binding.tvAddResume.setOnClickListener {
            viewModel.addResume(
                Resume(
                    basicInfo = true,
                    techStack = true,
                    education = true,
                    title = "이력서_240424",
                    date = "2024.04.24",
                    state = true
                )
            )
            dismiss()
        }
    }

}
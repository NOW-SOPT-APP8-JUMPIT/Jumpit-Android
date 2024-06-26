package com.sopt.now.jumpit.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sopt.now.jumpit.data.remote.request.ResumeEnrollRequest
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
        _binding = null
        super.onDestroyView()
    }

    private fun onBtnAddCloseClick() {
        binding.ivCloseAddDialog.setOnClickListener {
            dismiss()
        }
    }

    private fun onTextResumeAddClick() {
        binding.tvAddResume.setOnClickListener {
            viewModel.enrollResume(
                ResumeEnrollRequest(
                    title = "이력서_240522",
                    userId = 1,
                )
            )
            dismiss()
        }
    }

}
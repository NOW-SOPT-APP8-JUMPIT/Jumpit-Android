package com.sopt.now.jumpit.ui.resume

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentResumeBinding
import com.sopt.now.jumpit.ui.base.BindingFragment

class ResumeFragment : BindingFragment<FragmentResumeBinding>(R.layout.fragment_resume) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onResumeHelpBtnClick()
        onAttachmentHelpBtnClick()
    }

    private fun onResumeHelpBtnClick() {
        binding.btnResumeHelp.setOnClickListener {
            val originalImage = binding.btnResumeHelp.drawable

            PopupWindow(requireContext()).apply {
                contentView = layoutInflater.inflate(R.layout.popup_resume, null)
                setBackgroundDrawable(ColorDrawable(Color.WHITE))
                isFocusable = true
                showAsDropDown(binding.btnResumeHelp)

                setOnDismissListener {
                    binding.btnResumeHelp.setImageDrawable(originalImage)
                }

                contentView.setOnTouchListener { _, _ ->
                    dismiss()
                    true
                }
            }
            binding.btnResumeHelp.setImageResource(R.drawable.icn_help_select)
        }
    }

    private fun onAttachmentHelpBtnClick() {
        binding.btnAttachmentHelp.setOnClickListener {
            val originalImage = binding.btnAttachmentHelp.drawable
            PopupWindow(requireContext()).apply {
                contentView = layoutInflater.inflate(R.layout.popup_attachment, null)
                setBackgroundDrawable(ColorDrawable(Color.WHITE))
                isFocusable = true
                showAsDropDown(binding.btnAttachmentHelp)

                setOnDismissListener {
                    binding.btnAttachmentHelp.setImageDrawable(originalImage)
                }

                contentView.setOnTouchListener { _, _ ->
                    dismiss()
                    true
                }
            }
            binding.btnAttachmentHelp.setImageResource(R.drawable.icn_help_select)
        }
    }
}
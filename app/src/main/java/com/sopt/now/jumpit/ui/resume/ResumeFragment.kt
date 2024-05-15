package com.sopt.now.jumpit.ui.resume

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupWindow
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentResumeBinding
import com.sopt.now.jumpit.ui.base.BindingFragment

class ResumeFragment : BindingFragment<FragmentResumeBinding>(R.layout.fragment_resume) {
    private lateinit var resumeAdapter: ResumeAdapter
    private lateinit var viewModel: ResumeViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onResumeHelpBtnClick()
        onAttachmentHelpBtnClick()
        onResumeAddBtnClick()

        viewModel = ViewModelProvider(this)[ResumeViewModel::class.java]

        resumeAdapter = ResumeAdapter(requireActivity().supportFragmentManager)
        binding.rvMyResumeList.run {
            adapter = resumeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        if(viewModel.mockResumeList.value.isNullOrEmpty()) {
            binding.clNoResumeListArea.visibility = View.VISIBLE
            binding.clMyResumeListArea.visibility = View.INVISIBLE
        } else {
            binding.clNoResumeListArea.visibility = View.INVISIBLE
            binding.clMyResumeListArea.visibility = View.VISIBLE
        }

        Log.e("ResumeFragment", "onViewCreated: ${viewModel.mockResumeList.value}")
        viewModel.mockResumeList.observe(viewLifecycleOwner) {
            resumeAdapter.setResumeList(it)
        }
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

    private fun onResumeAddBtnClick() {
        binding.btnResumeAdd.setOnClickListener {
            showAddBottomSheetDialog()
        }
    }

    private fun showAddBottomSheetDialog() {
        val addBottomSheetDialog = AddBottomSheetDialogFragment()
        addBottomSheetDialog.show(
            requireActivity().supportFragmentManager,
            addBottomSheetDialog.tag
        )
    }
}
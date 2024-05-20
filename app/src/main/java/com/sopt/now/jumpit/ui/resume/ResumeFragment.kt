package com.sopt.now.jumpit.ui.resume

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentResumeBinding
import com.sopt.now.jumpit.ui.base.BindingFragment

class ResumeFragment : BindingFragment<FragmentResumeBinding>(R.layout.fragment_resume) {
    private lateinit var resumeAdapter: ResumeAdapter
    private val viewModel by activityViewModels<ResumeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onResumeHelpBtnClick()
        onAttachmentHelpBtnClick()
        onResumeAddBtnClick()
        initResumeAdapter()
        observeResumeList()
    }

    private fun initResumeAdapter() {
        resumeAdapter = ResumeAdapter(requireActivity().supportFragmentManager)
        binding.rvMyResumeList.run {
            adapter = resumeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeResumeList() {
        viewModel.mockResumeList.observe(viewLifecycleOwner) { resumeList ->
            if (resumeList.isEmpty()) {
                with(binding) {
                    clNoResumeListArea.visibility = View.VISIBLE
                    clMyResumeListArea.visibility = View.INVISIBLE
                }
            } else {
                with(binding) {
                    clNoResumeListArea.visibility = View.INVISIBLE
                    clMyResumeListArea.visibility = View.VISIBLE
                }
            }
            binding.tvMyResumeCount.text = resumeList.size.toString()
            resumeAdapter.setResumeList(resumeList)
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
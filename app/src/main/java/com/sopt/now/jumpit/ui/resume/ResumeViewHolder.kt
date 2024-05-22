package com.sopt.now.jumpit.ui.resume

import MyResumeResponse
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.data.remote.request.ResumePrivateRequest
import com.sopt.now.jumpit.databinding.ItemResumeBinding

class ResumeViewHolder(
    private val binding: ItemResumeBinding,
    private val onClick: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    private val viewModel = ViewModelProvider.NewInstanceFactory().create(ResumeViewModel::class.java)
    fun onBind(position: Int, resume: Resume, fragmentManager: FragmentManager) {
        with(binding) {
            tvResumeTitle.text = resume.title
            tvResumeDate.text = "240424"
            ivToggle.setImageResource(if (resume.isPrivate) R.drawable.ic_resume_toggleon else R.drawable.ic_resume_toggleoff)
            ivToggle.setOnClickListener {
                onClick()
                viewModel.privateResume(resume.id, ResumePrivateRequest(resume.isPrivate))
                resume.isPrivate = !resume.isPrivate
                ivToggle.setImageResource(if (resume.isPrivate) R.drawable.ic_resume_toggleon else R.drawable.ic_resume_toggleoff)
            }
            ivEdit.setOnClickListener {
                val editBottomSheetDialog = EditBottomSheetDialogFragment(position)
                editBottomSheetDialog.show(
                    fragmentManager,
                    editBottomSheetDialog.tag
                )
            }
        }
    }
}
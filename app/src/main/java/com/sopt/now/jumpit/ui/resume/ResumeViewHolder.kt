package com.sopt.now.jumpit.ui.resume

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.ItemResumeBinding

class ResumeViewHolder(
    private val binding: ItemResumeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(position: Int, resume: Resume, fragmentManager: FragmentManager) {
        with(binding) {
            ivBasicInfo.setImageResource(if (resume.basicInfo) R.drawable.ic_resume_green_dot else R.drawable.ic_resume_red_dot)
            ivTechStack.setImageResource(if (resume.techStack) R.drawable.ic_resume_green_dot else R.drawable.ic_resume_red_dot)
            ivEducation.setImageResource(if (resume.education) R.drawable.ic_resume_green_dot else R.drawable.ic_resume_red_dot)
            tvResumeTitle.text = resume.title
            tvResumeDate.text = resume.date
            ivToggle.setImageResource(if (resume.state) R.drawable.ic_resume_toggleon else R.drawable.ic_resume_toggleoff)
            ivToggle.setOnClickListener {
                resume.state = !resume.state
                ivToggle.setImageResource(if (resume.state) R.drawable.ic_resume_toggleon else R.drawable.ic_resume_toggleoff)
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
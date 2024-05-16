package com.sopt.now.jumpit.ui.resume

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.ItemResumeBinding

class ResumeViewHolder(
    private val binding: ItemResumeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(resume: Resume, fragmentManager: FragmentManager) {
        with(binding) {
            ivBasicInfo.setImageResource(if (resume.basicInfo) R.drawable.icn_dot_green else R.drawable.icn_dot_red)
            ivTechStack.setImageResource(if (resume.techStack) R.drawable.icn_dot_green else R.drawable.icn_dot_red)
            ivEducation.setImageResource(if (resume.education) R.drawable.icn_dot_green else R.drawable.icn_dot_red)
            tvResumeTitle.text = resume.title
            tvResumeDate.text = resume.date
            btnToggle.setImageResource(if (resume.state) R.drawable.icn_toggleon else R.drawable.icn_toggleoff)
            btnToggle.setOnClickListener {
                resume.state = !resume.state
                btnToggle.setImageResource(if (resume.state) R.drawable.icn_toggleon else R.drawable.icn_toggleoff)
            }
            btnEdit.setOnClickListener {
                val editBottomSheetDialog = EditBottomSheetDialogFragment()
                editBottomSheetDialog.show(
                    fragmentManager,
                    editBottomSheetDialog.tag
                )
            }
        }
    }
}
package com.sopt.now.jumpit.ui.resume

import MyResumeResponse
import android.content.SharedPreferences
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.data.remote.request.ResumePrivateRequest
import com.sopt.now.jumpit.databinding.ItemResumeBinding

class ResumeViewHolder(
    private val binding: ItemResumeBinding,
    private val onClick: (Long, Boolean) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(position: Int, resume: Resume, fragmentManager: FragmentManager) {
        with(binding) {
            tvResumeTitle.text = resume.title
            tvResumeDate.text = "2024.04.24"
            ivToggle.isSelected = resume.isPrivate
            ivToggle.setOnClickListener {
                onClick(resume.id, !resume.isPrivate)
                ivToggle.isSelected = !ivToggle.isSelected
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
package com.sopt.now.jumpit.ui.resume

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

class ResumeViewModel : ViewModel() {
    val mockResumeList = listOf<Resume>(
        Resume(
            basicInfo = false,
            techStack = false,
            education = false,
            title = "이력서_240424",
            date = "2024.04.24 등록",
            state = false
        ),
        Resume(
            basicInfo = true,
            techStack = false,
            education = true,
            title = "이력서_240424",
            date = "2024.04.24 등록",
            state = false
        ),
        Resume(
            basicInfo = true,
            techStack = false,
            education = false,
            title = "이력서_240424",
            date = "2024.04.24 등록",
            state = false
        ),
        Resume(
            basicInfo = true,
            techStack = true,
            education = true,
            title = "이력서_240424",
            date = "2024.04.24 등록",
            state = false
        ),
    )

    fun showEditBottomDialogSheet(fragment: Fragment) {
        val editBottomSheetDialog = EditBottomSheetDialogFragment()
        editBottomSheetDialog.show(
            fragment.childFragmentManager,
            editBottomSheetDialog.tag
        )
    }
}
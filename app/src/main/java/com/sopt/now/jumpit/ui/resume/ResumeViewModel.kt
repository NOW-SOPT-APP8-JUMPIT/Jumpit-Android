package com.sopt.now.jumpit.ui.resume

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResumeViewModel : ViewModel() {
    private val _mockResumeList = MutableLiveData<MutableList<Resume>>()
    val mockResumeList: LiveData<MutableList<Resume>>
        get() = _mockResumeList

    init {
        // 초기 데이터 설정
        _mockResumeList.value = mutableListOf(
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
                state = true
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
                state = true
            )
        )
    }

    fun addResume(resume : Resume) {
        val currentList = _mockResumeList.value ?: mutableListOf()
        currentList.add(resume)
        _mockResumeList.value = currentList
    }
}
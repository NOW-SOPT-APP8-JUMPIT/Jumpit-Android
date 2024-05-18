package com.sopt.now.jumpit.ui.resume

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResumeViewModel : ViewModel() {
    private val _mockResumeList = MutableLiveData<MutableList<Resume>>()
    val mockResumeList: LiveData<MutableList<Resume>>
        get() = _mockResumeList

    fun addResume(resume: Resume) {
        val currentList = _mockResumeList.value ?: mutableListOf()
        currentList.add(resume)
        _mockResumeList.value = currentList
    }

    fun deleteResume(position: Int) {
        val currentList = _mockResumeList.value ?: mutableListOf()
        currentList.removeAt(position)
        _mockResumeList.value = currentList
    }
}
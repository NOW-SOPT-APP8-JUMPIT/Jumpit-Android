package com.sopt.now.jumpit.ui.resume

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.jumpit.data.ServicePool
import com.sopt.now.jumpit.data.ServicePool.resumeService
import com.sopt.now.jumpit.data.remote.request.ResumeEnrollRequest
import com.sopt.now.jumpit.data.remote.request.ResumePrivateRequest
import kotlinx.coroutines.launch

class ResumeViewModel : ViewModel() {
    private val _resumeList = MutableLiveData<MutableList<Resume>>()
    val resumeList: LiveData<MutableList<Resume>>
        get() = _resumeList

    private val _enrollState = MutableLiveData<ResumeEnrollState>()
    val enrollState: LiveData<ResumeEnrollState> get() = _enrollState

    fun deleteResume(position: Int) {
        val currentList = _resumeList.value ?: mutableListOf()
        currentList.removeAt(position)
        _resumeList.value = currentList
    }

    fun enrollResume(request: ResumeEnrollRequest) {
        viewModelScope.launch {
            runCatching {
                resumeService.enrollResume(request)
            }.onSuccess {
                if(it.status in 200..299) {
                    _enrollState.value = ResumeEnrollState(true, it.message)
                    Log.d("ResumeViewModel", it.toString())
                } else {
                    _enrollState.value = ResumeEnrollState(false, it.message)
                }
            }.onFailure {
                _enrollState.value = ResumeEnrollState(false, it.message.toString())
            }
        }
    }

    fun getMyResume() {
        viewModelScope.launch {
            runCatching {
                resumeService.getMyResume(userId = 1)
            }.onSuccess {
                if (it.status in 200..299) {
                    val resumes = it.data?.resumes
                    if (resumes != null) {
                        _resumeList.postValue(
                            resumes.map {
                                Resume(
                                    id = it.id,
                                    title = it.title,
                                    isPrivate = it.isPrivate,
                                )
                            }.toMutableList()
                        )
                    }
                } else {
                    Log.e("ResumeViewModel", it.message)
                }
            }.onFailure {
                Log.e("ResumeViewModel", it.message.toString())
            }
        }
    }

    fun privateResume(resumeId: Long, request: ResumePrivateRequest) {
        viewModelScope.launch {
            runCatching {
                resumeService.privateResume(resumeId, request)
            }.onSuccess {
                if(it.status in 200..299) {
                    Log.d("privateResume", it.toString())
                } else {
                    Log.d("ResumeViewModel", it.message)
                }
            }.onFailure {
                Log.e("ResumeViewModel", it.message.toString())
            }
        }
    }
}
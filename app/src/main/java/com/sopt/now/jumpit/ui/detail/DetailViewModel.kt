package com.sopt.now.jumpit.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.jumpit.data.ServicePool.detailService
import com.sopt.now.jumpit.data.remote.response.DetailResponse
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val _detailInfo = MutableLiveData<DetailResponse>()
    val detailInfo: LiveData<DetailResponse> get() = _detailInfo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun getDetailInfo(positionId: Long) {
        _isLoading.value = true
        viewModelScope.launch {
            runCatching {
                detailService.getPositionDetail(positionId)
            }.onSuccess { response ->
                _isLoading.value = false
                if (response.status in 200..299) {
                    val temp = response.data
                    if(temp != null) {
                        _detailInfo.value = temp!!
                        _errorMessage.value = null
                    }
                } else {
                    _errorMessage.value = "Error: ${response.status}"
                }
            }.onFailure { throwable ->
                _isLoading.value = false
                Log.e("", "4")
                _errorMessage.value = throwable.message
            }
        }
    }
}
package com.example.funnymemes.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.funnymemes.model.MemeRepository
import com.example.funnymemes.model.response.MemeResponse


class MemeDetailViewModel (): ViewModel() {
    var memeDetailState = MutableLiveData<MemeResponse>()
    private val repository: MemeRepository = MemeRepository.getInstance()

    fun getMeme(memeId: String) {
        memeDetailState.value =  repository.getMeme(memeId)
    }
}
package com.example.funnymemes.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.funnymemes.model.MemeRepository
import com.example.funnymemes.model.response.MemeResponse
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemeListViewModel (private val repository: MemeRepository = MemeRepository.getInstance()): ViewModel() {
    //Create the live data to be observed by the view
     var memeState = MutableLiveData<List<MemeResponse>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            //Call the suspend method
            val memes = getMemes()
            //update the live data, as it's an async/background so use postValue
            memeState.postValue(memes)
        }
    }

    suspend fun getMemes(): List<MemeResponse> {
        //call the api using the MemeRepository instance
        return repository.getMemes().data.memes
    }
}
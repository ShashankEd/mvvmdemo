package com.example.funnymemes.model

import com.example.funnymemes.model.api.MemesWebService
import com.example.funnymemes.model.response.MemeListResponse
import com.example.funnymemes.model.response.MemeResponse

class MemeRepository(private val webService: MemesWebService = MemesWebService()) {
    private var cachedMemes = listOf<MemeResponse>()

    suspend fun getMemes(): MemeListResponse {
        val response = webService.getMemes()
        cachedMemes = response.data.memes
        return response
    }
    fun getMeme(id:String): MemeResponse? {
        return cachedMemes.firstOrNull {
            it.memeId == id
        }
    }
    companion object {
        private var instance:MemeRepository? = null
        fun getInstance() = instance?: synchronized(this) {
            instance ?: MemeRepository().also { instance = it }
        }
    }

}
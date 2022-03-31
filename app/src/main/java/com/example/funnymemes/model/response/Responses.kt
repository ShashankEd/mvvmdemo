package com.example.funnymemes.model.response

import com.google.gson.annotations.SerializedName
//Following are the mapping for the api's response
//https://api.imgflip.com/get_memes
data class MemeResponse(
    @SerializedName("id") val memeId:String,
    @SerializedName("name") val memeName:String,
    @SerializedName("url") val memeURL:String,
)

data class MemesList(
    val memes: List<MemeResponse>,
)
data class MemeListResponse(
    val success: Boolean,
    val data: MemesList
)
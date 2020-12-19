package com.example.rxjavaflatmapexample.models

import com.google.gson.annotations.SerializedName


data class Post(
    @SerializedName("userId") val userId: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("body") val body: String?,
    var comments: List<Comment>?
)
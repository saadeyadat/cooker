package com.example.cooker.other

import com.google.gson.annotations.SerializedName

data class ApiResponse(@SerializedName("hits") val imagesList: List<ApiImage>)

data class ApiImage(@SerializedName("previewURL") val image: String)
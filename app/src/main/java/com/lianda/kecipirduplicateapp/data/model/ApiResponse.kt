package com.lianda.kecipirduplicateapp.data.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: List<Product>,
    @SerializedName("message")
    val message: String
)
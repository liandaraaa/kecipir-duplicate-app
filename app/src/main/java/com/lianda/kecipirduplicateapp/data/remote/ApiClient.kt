package com.lianda.kecipirduplicateapp.data.remote

import com.lianda.kecipirduplicateapp.data.model.ProductResponse
import retrofit2.http.GET

interface ApiClient{
    @GET("test/products.json")
    suspend fun getProducts():ProductResponse
}
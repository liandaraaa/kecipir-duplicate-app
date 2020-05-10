package com.lianda.kecipirduplicateapp.data.remote

import com.lianda.kecipirduplicateapp.data.model.ProductResponse

class Api (val apiClient: ApiClient):ApiClient{
    override suspend fun getProducts(): ProductResponse {
        return apiClient.getProducts()
    }

}
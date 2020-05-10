package com.lianda.kecipirduplicateapp.data.source

import com.lianda.kecipirduplicateapp.data.model.Product


interface RepositoryImpl{
    suspend fun getProducts():List<Product>
}
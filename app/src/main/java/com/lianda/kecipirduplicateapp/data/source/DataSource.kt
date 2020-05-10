package com.lianda.kecipirduplicateapp.data.source

import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.data.remote.Api


class DataSource(val api: Api) : RepositoryImpl {

    override suspend fun getProducts(): List<Product> {
        return api.getProducts().data
    }

}
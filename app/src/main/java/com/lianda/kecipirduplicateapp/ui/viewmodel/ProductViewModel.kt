package com.lianda.kecipirduplicateapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lianda.kecipirduplicateapp.data.model.Product
import com.lianda.kecipirduplicateapp.data.source.RepositoryImpl
import com.lianda.kecipirduplicateapp.depth.service.model.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductViewModel(
    private val repository: RepositoryImpl
) : ViewModel() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    val products = MutableLiveData<Resource<List<Product>>>()
    
    fun getProducts() {
        products.value = Resource.loading()
        uiScope.launch {
            try {
                val datas = repository.getProducts()
                products.value = Resource.success(datas)
            } catch (e: Exception) {
                products.value = Resource.error(e.message)
                Log.d("Story Exception", e.message.toString())
            }
        }
    }
}
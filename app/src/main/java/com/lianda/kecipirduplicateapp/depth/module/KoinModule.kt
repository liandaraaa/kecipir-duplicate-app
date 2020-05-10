package com.lianda.kecipirduplicateapp.depth.module

import com.lianda.kecipirduplicateapp.depth.service.OkhttpClientFactory
import com.google.gson.Gson
import com.lianda.kecipirduplicateapp.BuildConfig
import com.lianda.kecipirduplicateapp.data.remote.Api
import com.lianda.kecipirduplicateapp.data.remote.ApiClient
import com.lianda.kecipirduplicateapp.data.source.DataSource
import com.lianda.kecipirduplicateapp.data.source.RepositoryImpl
import com.lianda.kecipirduplicateapp.depth.service.RetrofitService
import com.lianda.kecipirduplicateapp.ui.viewmodel.ProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named

import org.koin.dsl.module

const val BASE_URL = "base_url"

val serviceModule = module {
    single { return@single OkhttpClientFactory.create() }
    single(named(BASE_URL)) { BuildConfig.BASE_URL }
}

val utilityModule = module {
    single { Gson() }
}

//val preferenceModule = module {
//    single { StoryPreference(get()) }
//}
//
val productModule = module {
    single {
        RetrofitService.createReactiveService(
            ApiClient::class.java,
            get(),
            get(named(BASE_URL))
        )
    }
    single { Api(get()) }
    single<RepositoryImpl> { DataSource(get()) }
    viewModel { ProductViewModel(get()) }
}

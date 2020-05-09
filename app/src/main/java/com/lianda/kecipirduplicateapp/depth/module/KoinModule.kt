package com.lianda.kecipirduplicateapp.depth.module

import com.lianda.kecipirduplicateapp.depth.service.OkhttpClientFactory
import com.google.gson.Gson

import org.koin.dsl.module

const val BASE_URL = "base_url"

val serviceModule = module {
    single { return@single OkhttpClientFactory.create() }
//    single(named(BASE_URL)) { BuildConfig.BASE_URL }
}

val utilityModule = module {
    single { Gson() }
}

//val preferenceModule = module {
//    single { StoryPreference(get()) }
//}
//
//val storyModule = module {
//    single {
//        RetrofitService.createReactiveService(
//            ApiClient::class.java,
//            get(),
//            get(named(BASE_URL))
//        )
//    }
//    single { Api(get()) }
//    single<RepositoryImpl> { DataSource(get()) }
//    viewModel { StoryViewModel(get()) }
//}

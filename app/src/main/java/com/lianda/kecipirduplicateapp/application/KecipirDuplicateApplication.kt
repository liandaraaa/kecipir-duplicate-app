package com.lianda.kecipirduplicateapp.application

import android.app.Application
import com.lianda.kecipirduplicateapp.depth.koin.KoinContext
import com.lianda.kecipirduplicateapp.depth.module.serviceModule
import com.lianda.kecipirduplicateapp.depth.module.utilityModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KecipirDuplicateApplication :Application(){
    override fun onCreate() {
        super.onCreate()

        KoinContext.initialize(applicationContext)

        startKoin {
            androidContext(this@KecipirDuplicateApplication)
            modules(
                listOf(
                    serviceModule,
                    utilityModule
//                    preferenceModule,
//                    storyModule
                )
            )
        }

    }
}
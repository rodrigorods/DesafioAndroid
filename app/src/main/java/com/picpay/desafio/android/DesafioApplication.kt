package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.inject.networkModule
import com.picpay.desafio.inject.userManagementFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DesafioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DesafioApplication)
            modules(
                userManagementFeatureModule, networkModule
            )
        }
    }

}
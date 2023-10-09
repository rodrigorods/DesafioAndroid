package com.picpay.desafio.inject

import com.picpay.desafio.android.api.PicPayService
import com.picpay.desafio.android.createApi
import com.picpay.desafio.android.provideOkHttpClient
import com.picpay.desafio.android.provideRetrofit
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.picpay.desafio.ui.UserListViewModel
import com.picpay.desafio.android.usecase.GetAllUsersUseCase
import com.picpay.desafio.android.repository.UserManagementRepository
import com.picpay.desafio.android.repository.UserManagementRepositoryImpl
import com.picpay.desafio.android.usecase.GetAllUsersUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf

val userManagementFeatureModule = module {
    factory { Dispatchers.IO }

    factory { createApi<PicPayService>(get()) }
    factoryOf(::UserManagementRepositoryImpl) { bind<UserManagementRepository>() }
    factoryOf(::GetAllUsersUseCaseImpl) { bind<GetAllUsersUseCase>() }
    viewModelOf(::UserListViewModel)
}

val networkModule = module {
    factory { provideOkHttpClient() }
    single {
        provideRetrofit(
            apiUrl = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/",
            okHttpClient = get()
        )
    }
}
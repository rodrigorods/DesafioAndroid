package com.picpay.desafio.android.repository

import com.picpay.desafio.android.api.PicPayService

class UserManagementRepositoryImpl(
    private val api: PicPayService
) : UserManagementRepository {

    override suspend fun getAllUsers() = safeApiCall {
        api.getUsers()
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Result.success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }
}
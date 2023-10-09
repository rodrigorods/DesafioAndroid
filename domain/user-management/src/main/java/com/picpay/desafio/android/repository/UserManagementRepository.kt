package com.picpay.desafio.android.repository

import com.picpay.desafio.android.User

interface UserManagementRepository {
    suspend fun getAllUsers(): Result<List<User>>
}
package com.picpay.desafio.android.usecase

import com.picpay.desafio.android.User
import com.picpay.desafio.android.repository.UserManagementRepository

interface GetAllUsersUseCase {
    suspend fun getAllUsers(): Result<List<User>>
}

class GetAllUsersUseCaseImpl(
    private val repository: UserManagementRepository
) : GetAllUsersUseCase {
    override suspend fun getAllUsers(): Result<List<User>> {
        return repository.getAllUsers()
    }
}
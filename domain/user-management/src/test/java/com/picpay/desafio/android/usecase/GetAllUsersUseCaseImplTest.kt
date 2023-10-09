package com.picpay.desafio.android.usecase

import com.picpay.desafio.android.User
import com.picpay.desafio.android.repository.UserManagementRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllUsersUseCaseImplTest {

    private val repository = mockk<UserManagementRepository>()
    private val useCase = GetAllUsersUseCaseImpl(repository)

    @Test
    fun getAllUsers_shouldReturnExpectedListOfUsers() = runTest {
        val expectedResult = Result.success(emptyList<User>())
        coEvery { repository.getAllUsers() } returns expectedResult

        assertEquals(useCase.getAllUsers(), expectedResult)
    }
}

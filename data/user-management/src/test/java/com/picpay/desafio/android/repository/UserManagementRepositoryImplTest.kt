package com.picpay.desafio.android.repository

import com.picpay.desafio.android.User
import com.picpay.desafio.android.api.PicPayService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class UserManagementRepositoryImplTest {

    private val picPayApi = mockk<PicPayService>()
    private val repository = UserManagementRepositoryImpl(picPayApi)

    @Test
    fun getAllUsers_shouldReturnExpectedListOfUsers() = runTest {
        val expectedResult = listOf(
            User("img1", "name1", 9, "username1"),
            User("img2", "name2", 8, "username2"),
        )
        coEvery { picPayApi.getUsers() } returns expectedResult

        assertEquals(
            repository.getAllUsers(),
            Result.success(expectedResult)
        )
    }

    @Test
    fun getAllUsers_shouldReturnFailureWhenApiThrowsError() = runTest {
        val expectedException = Exception("This is some mocked exception")
        coEvery { picPayApi.getUsers() } throws expectedException

        assertEquals(
            repository.getAllUsers(),
            Result.failure<List<User>>(expectedException)
        )
    }
}

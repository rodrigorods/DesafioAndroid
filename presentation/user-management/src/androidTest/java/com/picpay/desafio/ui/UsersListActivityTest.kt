package com.picpay.desafio.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.User
import com.picpay.desafio.android.usecase.GetAllUsersUseCase
import com.picpay.desafio.android.usecase.GetAllUsersUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class UsersListActivityTest {

    private val userCase = mockk<GetAllUsersUseCaseImpl>()

    @Before
    fun setup() {
        startKoin {modules(
            module {
                factory<GetAllUsersUseCase> { userCase }
                viewModel { UserListViewModel(Dispatchers.IO, getAllUsersUseCase = get()) }
            })
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun onInit_titleShouldBeVisible() {
        mockSuccessfulApiResponse()
        launch {
            checkTitleTextIsDisplayed(R.string.title)
        }
    }

    @Test
    fun onSuccessfulApiCall_shouldDisplayListOfUsers() {
        mockSuccessfulApiResponse()
        launch {
            checkUserNameAtPosition("userName1", 0)
            checkUserNameAtPosition("userName2", 1)
            checkUserNameAtPosition("userName3", 2)
        }
    }

    @Test
    fun onFailedApiCall_shouldDisplayTryAgainScreen() {
        mockFailedApiResponse()
        launch {
            checkErrorScreenIsVisible()
        }
    }

    private fun mockSuccessfulApiResponse() {
        coEvery {
            userCase.getAllUsers()
        } returns Result.success(
            listOf(
                User("img", "First User Name1", 1, "userName1"),
                User("img", "Second Name 222", 2, "userName2"),
                User("img", "Rodrigo Souza", 3, "userName3"),
            )
        )
    }

    private fun mockFailedApiResponse() {
        coEvery { userCase.getAllUsers() } returns Result.failure(Throwable("mocked error"))
    }
}
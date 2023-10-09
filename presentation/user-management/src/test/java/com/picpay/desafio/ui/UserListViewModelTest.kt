package com.picpay.desafio.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.User
import com.picpay.desafio.android.usecase.GetAllUsersUseCase
import com.picpay.desafio.ui.util.MainDispatcherRule
import com.picpay.desafio.ui.util.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class UserListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockkUseCase = mockk<GetAllUsersUseCase>()
    private val successfulResult: Result<List<User>> = Result.success(emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllUsers_emitFullScreenProgressFirst_thenUserListUiState() = runTest {
        coEvery { mockkUseCase.getAllUsers() } returns successfulResult

        val viewModel = UserListViewModel(
            ioDispatcher = mainDispatcherRule.testDispatcher,
            getAllUsersUseCase = mockkUseCase
        )

        viewModel.getAllUsers()
        assertEquals(
            viewModel.uiState.getOrAwaitValue(), UIState.FullPageLoading
        )

        advanceUntilIdle()
        assertTrue(
            viewModel.uiState.value is UIState.DisplayingCharacterList
        )
    }
}
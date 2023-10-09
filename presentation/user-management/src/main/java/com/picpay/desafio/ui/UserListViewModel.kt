package com.picpay.desafio.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.User
import com.picpay.desafio.android.usecase.GetAllUsersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class UIState {
    object FullPageLoading : UIState()
    data class DisplayingCharacterList(val users: List<User>) : UIState()
    object DefaultError : UIState()
}

class UserListViewModel(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>(UIState.FullPageLoading)
    val uiState: LiveData<UIState> = _uiState

    fun getAllUsers() {
        viewModelScope.launch(ioDispatcher) {
            getAllUsersUseCase.getAllUsers()
                .onFailure { _uiState.postValue(UIState.DefaultError) }
                .onSuccess { _uiState.postValue(UIState.DisplayingCharacterList(it)) }
        }
    }

    fun retryGetAllUsers() {
        _uiState.postValue(UIState.FullPageLoading)
        getAllUsers()
    }

}
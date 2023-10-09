package com.picpay.desafio.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.User
import com.picpay.desafio.ui.databinding.ActivityUsersListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersListActivity : AppCompatActivity() {

    private val binding: ActivityUsersListBinding by lazy {
        ActivityUsersListBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<UserListViewModel>()
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupUi()

        viewModel.getAllUsers()
        viewModel.uiState.observe(this) {
            when (it) {
                is UIState.FullPageLoading -> displayFullScreenProgress()
                is UIState.DisplayingCharacterList -> displayUserList(it.users)
                is UIState.DefaultError -> displayRetryScreen()
            }
        }
    }

    private fun setupUi() {
        userListAdapter = UserListAdapter()
        binding.recyclerView.adapter = userListAdapter
        binding.errorContent.retryButton.setOnClickListener {
            viewModel.retryGetAllUsers()
        }
    }

    private fun displayFullScreenProgress() {
        binding.userListProgressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.errorContent.root.visibility = View.GONE
    }

    private fun displayUserList(users: List<User>) {
        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.errorContent.root.visibility = View.GONE

        userListAdapter.users = users
    }

    private fun displayRetryScreen() {
        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.errorContent.root.visibility = View.VISIBLE
    }
}

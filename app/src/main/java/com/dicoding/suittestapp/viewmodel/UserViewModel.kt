package com.dicoding.suittestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.suittestapp.model.User
import com.dicoding.suittestapp.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchUsers(page: Int, perPage: Int) {
        viewModelScope.launch {
            userRepository.fetchUsers(page, perPage, { userList ->
                _users.value = userList
            }, { throwable ->
                _error.value = throwable.message
            })
        }
    }
}
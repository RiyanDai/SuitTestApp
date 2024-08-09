package com.dicoding.suittestapp.repository

import com.dicoding.suittestapp.model.User
import com.dicoding.suittestapp.model.UserResponse
import com.dicoding.suittestapp.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun fetchUsers(page: Int, perPage: Int, onSuccess: (List<User>) -> Unit, onFailure: (Throwable) -> Unit) {
        ApiClient.service.getUsers(page, perPage).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let { users ->
                        onSuccess(users)
                    } ?: onFailure(Throwable("No users found"))
                } else {
                    onFailure(Throwable("Failed to load users"))
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onFailure(t)
            }
        })
    }
}
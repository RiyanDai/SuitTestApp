package com.dicoding.suittestapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.suittestapp.R
import com.dicoding.suittestapp.adapter.UserAdapter
import com.dicoding.suittestapp.databinding.ActivityThirdBinding
import com.dicoding.suittestapp.viewmodel.UserViewModel

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    private var currentPage = 1
    private val perPage = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        userViewModel.fetchUsers(currentPage, perPage)

        userViewModel.users.observe(this, { users ->
            userAdapter.addUsers(users)
        })

        userViewModel.error.observe(this, { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            userAdapter.clearUsers()
            currentPage = 1
            userViewModel.fetchUsers(currentPage, perPage)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.recyclerView.canScrollVertically(1)) {
                    currentPage++
                    userViewModel.fetchUsers(currentPage, perPage)
                }
            }
        })

        // Handle the back button click
        binding.btnback3.setOnClickListener {
            finish()  // This will close the current activity and return to the previous one
        }
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter { user ->
            val selectedUserName = "${user.first_name} ${user.last_name}"
            // Handle user selection and update the second screen
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ThirdActivity)
            adapter = userAdapter
        }
    }
}
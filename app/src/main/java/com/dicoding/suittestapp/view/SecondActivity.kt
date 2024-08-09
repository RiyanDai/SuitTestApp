package com.dicoding.suittestapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.suittestapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("USER_NAME")
        binding.userNameTextView.text = userName

        binding.chooseUserButton.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        // Handle the back button click
        binding.btnback2.setOnClickListener {
            finish()  // This will close the current activity and return to the previous one
        }
    }

    override fun onResume() {
        super.onResume()
        // Update the selected user name if available
        val selectedUserName = intent.getStringExtra("SELECTED_USER_NAME")
        if (!selectedUserName.isNullOrEmpty()) {
            binding.selectedUserNameTextView.text = selectedUserName
        }
    }
}
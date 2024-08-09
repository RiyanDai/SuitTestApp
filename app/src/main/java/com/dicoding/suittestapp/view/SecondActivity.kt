package com.dicoding.suittestapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.suittestapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    companion object {
        const val REQUEST_CODE_SELECT_USER = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("USER_NAME")
        binding.userNameTextView.text = userName

        binding.chooseUserButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SELECT_USER)
        }

        binding.btnback2.setOnClickListener {
            finish()  // Closes SecondActivity and returns to the previous one
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_USER && resultCode == RESULT_OK) {
            val selectedUserName = data?.getStringExtra("SELECTED_USER_NAME")
            binding.selectedUserNameTextView.text = selectedUserName
        }
    }
}
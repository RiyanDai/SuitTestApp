package com.dicoding.suittestapp.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.suittestapp.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkButton.setOnClickListener {
            val sentence = binding.sentenceEditText.text.toString()
            val isPalindrome = isPalindrome(sentence)
            val message = if (isPalindrome) "is Palindrome" else "Not Palindrome"

            AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        }

        binding.nextButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
        }
    }

    private fun isPalindrome(text: String): Boolean {
        // Remove non-alphanumeric characters and convert to lowercase
        val cleanedText = text.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
        return cleanedText == cleanedText.reversed()
    }
}
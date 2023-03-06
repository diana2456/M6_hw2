@file:Suppress("DEPRECATION")

package com.example.m6_hw2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.m6_hw2.databinding.ActivityGetBinding

class GetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetBinding
    private val adapter = GetAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetBinding.inflate(layoutInflater)

        binding.rvGet.adapter = adapter
        data()
        setContentView(binding.root)
        back()
    }

   private fun data(){
        val uri: ArrayList<Uri>? = intent.getParcelableArrayListExtra(MainActivity.IMAGE)
        if (uri != null) {
            adapter.addPhoto(uri)
        }
    }
   private fun back(){
        binding.tvBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            activityResultLauncher.launch(intent)
        }
    }

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                }
            }
        }
}
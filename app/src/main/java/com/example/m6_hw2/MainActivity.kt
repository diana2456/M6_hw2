@file:Suppress("KotlinConstantConditions")

package com.example.m6_hw2

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.m6_hw2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterPhoto: AdapterPhoto
    private var list = arrayListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapterPhoto = AdapterPhoto(this::click, this::deleteClick)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.rvMain.adapter = adapterPhoto

        showStoragePermission()
        setContentView(binding.root)
        seOnClick()
    }

    private fun seOnClick() {
        binding.tvAdd.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_PICK
                intent.type = "image/*"
                intent.putExtra(Intent.ACTION_PICK, true)
                activityResultLauncher.launch(intent)
        }
    }


  private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { adapterPhoto.addTask(it) }
            }
        }
      @SuppressLint("SetTextI18n")
      private  fun click(pos: Uri) {
            list.addAll(listOf(pos))
            if (list.size >= 0) with(binding) {
                cvDio.visibility = View.VISIBLE
                tvDio.text = getString(R.string.selected) + " " + list.size + getString(R.string.photographs)
                btnDio.setOnClickListener {
                    openActivity(list)
                }
            }
        }
    private fun openActivity(imageList: ArrayList<Uri>) {
    val  intent =  Intent(this@MainActivity, GetActivity::class.java)
           intent.putExtra(IMAGE, imageList)
            activityResultLauncher.launch(intent)

    }

      @SuppressLint("SetTextI18n")
      private fun deleteClick(pos: Uri) {
            list.remove(pos)
            if (list.size >= 0) with(binding) {
                tvDio.text = getString(R.string.selected) + list.size + getString(R.string.photographs)
            }
        }

        private fun showStoragePermission() {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    KEY
                )
            }
        }
        companion object {
            const val IMAGE = "list"
            const val KEY = 101
        }
    }


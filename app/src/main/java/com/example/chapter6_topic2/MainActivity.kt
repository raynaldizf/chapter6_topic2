package com.example.chapter6_topic2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import com.example.chapter6_topic2.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var userPrefs: UserPrefs
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPrefs = UserPrefs(this)

        binding.btnSave.setOnClickListener{
            val name = binding.etName.text.toString()
            val age = binding.etAge.text.toString().toInt()

            GlobalScope.launch {
                userPrefs.saveData(name,age)
            }
        }

        binding.txtName.text = userPrefs.userName.toString()
        binding.txtAge.text = userPrefs.userAge.toString()

        binding.btnClear.setOnClickListener{
            GlobalScope.launch {
                userPrefs.deleteData()
            }
        }
    }
}
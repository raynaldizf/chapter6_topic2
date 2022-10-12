package com.example.chapter6_topic2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
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


        userPrefs.userName.asLiveData().observe(this,{
            binding.txtName.text = it
        })

        userPrefs.userAge.asLiveData().observe(this,{
            binding.txtAge.text = it.toString()
        })


//        binding.txtName.text = userPrefs.userName.toString()
//        binding.txtAge.text = userPrefs.userAge.toString()

        binding.btnClear.setOnClickListener{
            GlobalScope.launch {
                userPrefs.deleteData()
            }
        }


    }
}
package com.example.idletravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.idletravel.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this@MainActivity))
        setContentView(binding.root)

        binding.mainStartButton.setOnClickListener{
            val intent = Intent()
            intent.setClass(this@MainActivity,CreatePlayerActivity::class.java)
            startActivity(intent)
        }

        binding.mainContinueButton.setOnClickListener {
            //TODO: 他妈反正你得写个读档操作
        }

        binding.mainExitButton.setOnClickListener {
            exitProcess(0)
        }
    }
}

/*
    程序流程: MainActivity -> CreatePlayer -> StartGame
                       or -> 继续的那个界面 -> StartGame
*/
package com.example.idletravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainStartButton.setOnClickListener{
            val intent = Intent()
            intent.setClass(this@MainActivity,CreatePlayer::class.java)
            startActivity(intent)
        }

        mainContinueButton.setOnClickListener {
            //TODO: 他妈反正你得写个读档操作
        }

        mainExitButton.setOnClickListener {
            exitProcess(0)
        }
    }
}

/*
    程序流程: MainActivity -> CreatePlayer -> StartGame
                       or -> 继续的那个界面 -> StartGame
*/
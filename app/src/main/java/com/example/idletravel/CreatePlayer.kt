package com.example.idletravel

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.customItem.ItemMap
import com.example.idletravel.player.Player
import kotlinx.android.synthetic.main.activity_create_player.*


class CreatePlayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_player)

        createPlayerFinishButton.setOnClickListener {
            val player = checkWidgetsAreFilled()
            if (player != null) {
                val intent = Intent(this@CreatePlayer, StartGame::class.java)
                val item= ItemMap(mutableMapOf())
                val bundle = Bundle()

                intent.putExtra("player", player)
                bundle.putSerializable("item", item)
                intent.putExtras(bundle)
                startActivity(intent)

            }
        }
    }

    private fun checkWidgetsAreFilled(): Player? {
        // 必须都填满了才能返回
        val name = createPlayerNameEditText.checkBlank() ?: return null
        val sex = createPlayerSexEditText.checkBlank() ?: return null
        val age = createPlayerAgeEditText.checkBlank() ?: return null
        return Player(name, sex, age.toInt())
    }

    private fun TextView.checkBlank(): String? {
        val text = this.text.toString()
        if (text.isBlank()) {
            return null
        }
        return text
    }
}


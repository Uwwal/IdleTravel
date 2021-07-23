package com.example.idletravel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.idletravel.customItem.CustomItem.*
import com.example.idletravel.customItem.ItemMap
import com.example.idletravel.databinding.ActivityCreatePlayerBinding
import com.example.idletravel.format.formatPlayerStatusTextTwoLines
import com.example.idletravel.player.Player


class CreatePlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePlayerBinding
    var status: MutableList<Double> = mutableListOf(0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00)
    // 力量 体质 灵巧 感知 学识 意志 魔力 魅力


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePlayerBinding.inflate(LayoutInflater.from(this@CreatePlayerActivity))
        setContentView(binding.root)

        rollStatus()

        binding.createPlayerRollButton.setOnClickListener {
            rollStatus()
        }

        binding.createPlayerFinishButton.setOnClickListener {
            val player = checkWidgetsAreFilled()
            if (player != null) {
                val intent = Intent(this@CreatePlayerActivity, StartGameActivity::class.java)
                val item = ItemMap(mutableMapOf(GARBAGE.name to 2))
                val bundle = Bundle()

                bundle.putSerializable("item", item)
                bundle.putSerializable("player",player)
                intent.putExtras(bundle)
                startActivity(intent)

            }
        }
    }

    private fun rollStatus() {
        for (i in status.indices) {
            status[i] = randStatus().toDouble()
        }

        binding.createPlayerStatusTextView.text = formatPlayerStatusTextTwoLines(status)
    }

    private fun randStatus() = (Math.random() * 9 + 1).toInt()

    private fun checkWidgetsAreFilled(): Player? {
        // 必须都填满了才能返回
        val name = binding.createPlayerNameEditText.checkBlank() ?: return null
        val sex = binding.createPlayerSexEditText.checkBlank() ?: return null
        return Player(name, sex, status)
    }

    private fun TextView.checkBlank(): String? {
        val text = this.text.toString()
        if (text.isBlank()) {
            return null
        }
        return text
    }
}


package com.example.idletravel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.idletravel.customItem.CustomItem.*
import com.example.idletravel.transmissionMap.TransmissionMap
import com.example.idletravel.databinding.ActivityCreatePlayerBinding
import com.example.idletravel.format.formatPlayerStatusTextTwoLines
import com.example.idletravel.gameCalender.GameCalendar
import com.example.idletravel.player.Player


class CreatePlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePlayerBinding
    private var status: MutableList<Double> = MutableList(10) { genRandStatus() }
    // 力量 体质 灵巧 感知 学识 意志 魔力 魅力
    private val gameCalendar = GameCalendar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePlayerBinding.inflate(LayoutInflater.from(this@CreatePlayerActivity))
        setContentView(binding.root)

        refreshStatus()
    }

    private fun genRandStatus(): Double = (1 until 10).random().toDouble()

    private fun refreshStatus() {
        binding.createPlayerStatusTextView.text = formatPlayerStatusTextTwoLines(status)
    }

    fun rollStatus(view: View) {
        status.replaceAll { genRandStatus() }
        refreshStatus()
    }

    fun finishCreation(view: View) {
        checkWidgetsAreFilled()?.let { player ->
            val intent = Intent(this@CreatePlayerActivity, StartGameActivity::class.java)
            val item = TransmissionMap(mutableMapOf())
            val bundle = Bundle()

            bundle.putSerializable("item", item)
            bundle.putSerializable("player",player)
            bundle.putSerializable("gameCalendar", gameCalendar)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun checkWidgetsAreFilled(): Player? {
        // 必须都填满了才能返回
        val name = binding.createPlayerNameEditText.checkBlank() ?: return null
        val sex = binding.createPlayerSexEditText.checkBlank() ?: return null
        return Player(name, sex, status)
    }

    private fun TextView.checkBlank(): String? {
        val text = this.text
        return if (text.isBlank()) null else text.toString()
    }
}

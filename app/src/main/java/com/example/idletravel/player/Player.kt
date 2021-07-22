package com.example.idletravel.player

import com.example.idletravel.area.Area
import com.example.idletravel.area.maps.grasslandArea
import java.io.Serializable

class Player(
    val name: String,
    var sex: String,
    var age: Number,
    var status: MutableList<Double> = mutableListOf(0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00),
    // 力量 体质 灵巧 感知 学识 意志 魔力 魅力
    var finishMapTime: HashMap<Area, Int> = hashMapOf(
        grasslandArea to 0
    )
) : Serializable {

}

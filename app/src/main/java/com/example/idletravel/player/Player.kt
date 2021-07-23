package com.example.idletravel.player

import com.example.idletravel.area.Area
import com.example.idletravel.area.maps.grasslandArea
import java.io.Serializable

class Player(
    val name: String,
    var sex: String,
    var status: MutableList<Double> = mutableListOf(0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00),
    // 力量 体质 灵巧 感知 学识 意志 魔力 魅力
    // 这里不要把list改成枚举类 血的教训
    // 如果改成枚举类, 则还需要一个list来保证随机奖励属性功能正常运行(位置在Travel.kt)
    var finishMapTime: HashMap<Area, Int> = hashMapOf(
        grasslandArea to 0
    )
) : Serializable {

}

package com.example.idletravel.player

import com.example.idletravel.area.CustomArea
import java.io.Serializable

class Player(
    val name: String,
    var sex: String,
    var status: MutableList<Double> = mutableListOf(0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00),
    // 力量 体质 灵巧 感知 学识 意志 魔力 魅力
    // 这里不要把list改成枚举类 血的教训
    // 如果改成枚举类, 则还需要一个list来保证随机奖励属性功能正常运行(位置在Travel.kt)
    var finishMapTime: HashMap<String, Int> = hashMapOf(
        CustomArea.LAWN.name to 0
    )
) : Serializable {
    val getStatus: (String) -> Double ={statusName->
        when(statusName){
            "力量"->status[0]
            "体质"->status[1]
            "灵巧"->status[2]
            "感知"->status[3]
            "学识"->status[4]
            "意志"->status[5]
            "魔力"->status[6]
            "魅力"->status[7]
            else -> status[0]
        }
    }
    companion object {
        val statusNames = arrayOf("力量", "体质", "灵巧", "感知", "学识", "意志", "魔力", "魅力")
    }
}

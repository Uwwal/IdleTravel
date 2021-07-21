package com.example.idletravel.player

import java.io.Serializable

class Player constructor(
    val name: String,
    var sex: String,
    var age: Number,
    var status: MutableList<Int> = mutableListOf(0,0,0,0,0,0,0,0)
    // 力量 体质 灵巧 感知 学识 意志 魔力 魅力
) : Serializable {

}

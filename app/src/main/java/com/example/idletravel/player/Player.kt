package com.example.idletravel.player

import java.io.Serializable

class Player constructor(
    val name: String,
    var sex: String,
    var age: Number,
    var str: Int = 0,   // 力量
    var con: Int = 0,   // 体质
    var dex: Int = 0,   // 灵巧
    var per: Int = 0,   // 感知
    var ler: Int = 0,   // 学识
    var wil: Int = 0,   // 意志
    var mag: Int = 0,   // 魔力
    var chr: Int = 0,   // 魅力
) : Serializable {

}

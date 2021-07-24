package com.example.idletravel.area

import com.example.idletravel.area.maps.lawnDropsList
import com.example.idletravel.area.maps.lawnDropsMap
import com.example.idletravel.area.maps.lawnDropsWeightList
import com.example.idletravel.format.informationBlank
import java.io.Serializable

enum class CustomArea(
    val areaName: String,
    var information: String,
    val travelTime: Int, // 旅行时间 设想是从5到600
    private val dropsMap: HashMap<String, () -> Boolean>, // 掉落物检查表, value值是个lambda
    val dropsNumber: Int, // 掉落量 设想是从3到100
    private val dropsWeightList: List<Int>, // 掉落物权重
    private val dropsList: List<String>, // 掉落物列表 和掉落物权重index相同
    val upperLimitOfBonusStatus: Int = 9, // 奖励属性上限值
    val lowerLimitOfBonusStatus: Int = 0, // 下限值
) : Serializable,Area {
    grasslandArea(
        "草地",
        "家门口的草地, 与其说是旅行不如说是散步...\n" +
                informationBlank + "稀稀疏疏的分布着一些灌木, 地上散落着没素质的路人留下的垃圾. 总之就是很乏味啦.\n" +
                informationBlank + "当然你如果想拒绝乏味的话也可以考虑和昆虫, 兔子之类的斗智斗勇.. 很要求技术就是了.",
        5,
        lawnDropsMap,
        3,
        lawnDropsWeightList,
        lawnDropsList,
        9,
        0
    )

}
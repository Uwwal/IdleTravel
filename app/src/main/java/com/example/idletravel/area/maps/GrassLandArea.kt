package com.example.idletravel.area.maps

import com.example.idletravel.area.Area
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.customItem.items.*
import com.example.idletravel.format.areaInformationBlank
import com.example.idletravel.player

// player status 力量 体质 灵巧 感知 学识 意志 魔力 魅力
//        index  0    1    2    3    4    5    6    7

private val dropsList = listOf(
    garbageItem, softTwigItem, berryItem, ladybugItem, longHornedBeetleItem,
    antItem, rabbitItem
)
private val dropsMap: Map<CustomItem, () -> Boolean> =
    mapOf(
        garbageItem to { true },
        softTwigItem to { player.status[2] > 2 },
        berryItem to { player.status[4] > 3 },
        ladybugItem to { player.status[3] > 1 && player.status[4] > 1 },
        longHornedBeetleItem to {
            (player.status[2] > 4 || (player.status[1] * player.status[5] > 8)) && player.status[4] > 1
                                },
        antItem to {true},
        rabbitItem to {player.status[2]*player.status[3]>64 && player.status[5]>5}
    )
private val dropsWeightList = listOf(30, 10, 5, 5, 5, 10, 1)

val grasslandArea = Area(
    "草地",
    "家门口的草地, 与其说是旅行不如说是散步...\n" +
            areaInformationBlank + "稀稀疏疏的分布着一些灌木, 地上散落着没素质的路人留下的垃圾. 总之就是很乏味啦.\n" +
            areaInformationBlank + "当然你如果想拒绝乏味的话也可以考虑和昆虫, 兔子之类的斗智斗勇.. 很要求技术就是了.",
    5,
    dropsMap,
    3,
    dropsWeightList,
    dropsList
)

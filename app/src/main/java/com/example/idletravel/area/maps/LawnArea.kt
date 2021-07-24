package com.example.idletravel.area.maps

import com.example.idletravel.customItem.CustomItem.*
import com.example.idletravel.player

// player status 力量 体质 灵巧 感知 学识 意志 魔力 魅力
//        index  0    1    2    3    4    5    6    7

val lawnDropsList = listOf(
    GARBAGE.name, SOFT_TWIG.name, BERRY.name, LADYBUG.name, LONG_HORNED_BEETLE.name,
    ANT.name, RABBIT.name
)
val lawnDropsMap: HashMap<String, () -> Boolean> =
    hashMapOf(
        GARBAGE.name to { true },
        SOFT_TWIG.name to { player.status[2] > 2 },
        BERRY.name to { player.status[4] > 3 },
        LADYBUG.name to { player.status[3] > 1 && player.status[4] > 1 },
        LONG_HORNED_BEETLE.name to {
            (player.status[2] > 4 || (player.status[1] * player.status[5] > 8)) && player.status[4] > 1
                                },
        ANT.name to {true},
        RABBIT.name to {player.status[2]*player.status[3]>64 && player.status[5]>5}
    )
val lawnDropsWeightList = listOf(30, 10, 5, 5, 5, 10, 1)
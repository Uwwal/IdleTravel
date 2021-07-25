package com.example.idletravel.area.maps

import com.example.idletravel.area.judgeItemAcquisition.enumClass.JudgeBerry
import com.example.idletravel.area.judgeItemAcquisition.enumClass.JudgeInsect
import com.example.idletravel.area.judgeItemAcquisition.enumClass.JudgeSmallAnimal
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
        BERRY.name to { JudgeBerry.BERRY.getJudgement() },
        LADYBUG.name to { JudgeInsect.LADYBUG.getJudgement() },
        LONG_HORNED_BEETLE.name to { JudgeInsect.LONG_HORNED_BEETLE.getJudgement() },
        ANT.name to { JudgeInsect.ANT.getJudgement() },
        RABBIT.name to { JudgeSmallAnimal.RABBIT.getJudgement() },
    )
val lawnDropsWeightList = listOf(30, 10, 5, 5, 5, 10, 1)
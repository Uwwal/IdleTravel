package com.example.idletravel.area.judgeItemAcquisition.enumClass

import com.example.idletravel.area.judgeItemAcquisition.judgeInterface.JudgePlant
import com.example.idletravel.gameCalender.Season

enum class JudgeBurry(
    override val size: Int, // 大小 上不封顶
    override val concealment: Int, // 隐蔽 上限10
    override val discoverDifficulty: Int = 2, // 发现难度乘数 枝繁叶茂, size<3
    override val pickingDifficulty: Int = 1, // 采摘难度乘数 柔弱的
    override val camouflageAbility: Int = 0, // 伪装, 上不封顶
    override val distinguishDifficult: Int = 0, // 辨识难度乘数
    override val season: List<Season> =
        listOf(Season.AUTUMN, Season.SUMMER), // 出现季节, 默认浆果春季不熟
) : JudgePlant {
    // 浆果, 莓果等
    BURRY(2, 2, 2, 1)
    ;

    override val getJudgement: () -> Boolean = {
        discover() && distinguish() && pick()
    }
}
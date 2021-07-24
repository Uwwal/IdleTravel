package com.example.idletravel.area.judgeItemAcquisition.enumClass

import com.example.idletravel.area.judgeItemAcquisition.judgeInterface.JudgeAnimal
import com.example.idletravel.gameCalender.Season

enum class JudgeInsect(
    override val size: Int = 0, // 大小 上不封顶 有关说明见Judge
    override val power: Int = 0, // 力量 上不封顶 有关说明见Judge
    override val aggressiveness: Int = 0, // 攻击性 上限10 有关说明见Judge
    override val speed: Int = 0, // 速度 上不封顶 有关说明见Judge
    override val concealment: Int = 0, // 隐蔽 上限10 有关说明见Judge
    override val season: List<Season> = listOf(
        Season.SPRING,
        Season.SUMMER,
        Season.AUTUMN
    ), // 默认昆虫冬季不出现
    override val avoidingBattle: Boolean = false,
    override val winWithSpeedDifferent: Int = 2
) : JudgeAnimal {
    LADYBUG(1, 0, 0, 10, 3),
    LONG_HORNED_BEETLE(3, 15, 3, 4, 2),
    ANT(1, 1, 2, 4, 2),
    ;

    override val getJudgement: () -> Boolean = {
        discover() && (winWithSpeed() || (win())) && judgeWithSeason()
    }
}
// 力量 体质 灵巧 感知 学识 意志 魔力 魅力
// 0    1    2    3    4    5    6    7
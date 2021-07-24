package com.example.idletravel.area.judgeItemAcquisition.enumClass

import com.example.idletravel.area.judgeItemAcquisition.judgeInterface.JudgeAnimal
import com.example.idletravel.gameCalender.Season

enum class JudgeSmallAnimal(
    override val size: Int = 0, // 大小 上不封顶 有关说明见Judge
    override val power: Int = 0, // 力量 上不封顶 有关说明见Judge
    override val aggressiveness: Int = 0, // 攻击性 上限10 有关说明见Judge
    override val speed: Int = 0, // 速度 上不封顶 有关说明见Judge
    override val concealment: Int = 0, // 隐蔽 上限10 有关说明见Judge
    override val season: List<Season> = listOf(
        Season.SPRING,
        Season.SUMMER,
        Season.AUTUMN
    ), // 默认小动物冬季不出现
    override val avoidingBattle: Boolean = false,
    override val winWithSpeedDifferent: Int = 3
) : JudgeAnimal {
    RIBBIT(3, speed = 40, concealment = 6, avoidingBattle = true)
    ;
    override val getJudgement: () -> Boolean = {
        discover() && (winWithSpeed() || (win())) && judgeWithSeason()
    }
}

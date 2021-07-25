package com.example.idletravel.area.judgeItemAcquisition.enumClass

import com.example.idletravel.area.judgeItemAcquisition.judgeInterface.JudgeAnimal
import com.example.idletravel.gameCalender.Season

enum class JudgeSmallAnimal(
    override val size: Int = 0, // 大小
    override val power: Int = 0, // 力量
    override val aggressiveness: Int = 0, // 攻击性
    override val speed: Int = 0, // 速度
    override val concealment: Int = 0, // 隐蔽
    override val season: List<Season> = listOf(
        Season.SPRING,
        Season.SUMMER,
        Season.AUTUMN
    ), // 默认小动物冬季不出现
    override val avoidingBattle: Boolean = false,
    override val winWithSpeedDifferent: Int = 1 // 大小小于10
) : JudgeAnimal {
    RABBIT(7, speed = 100, concealment = 7, avoidingBattle = true, winWithSpeedDifferent = 3) // 大小小于10, 有洞穴
    ;
    override val getJudgement: () -> Boolean = {
        discover() && (winWithSpeed() || (win())) && judgeWithSeason()
    }
}

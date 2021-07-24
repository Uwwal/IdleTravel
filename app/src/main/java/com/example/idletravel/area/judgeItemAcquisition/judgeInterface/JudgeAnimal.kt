package com.example.idletravel.area.judgeItemAcquisition.judgeInterface

import com.example.idletravel.player

interface JudgeAnimal : Judge {
    val power: Int // 力量 上不封顶
    val aggressiveness: Int // 攻击性 上限10
    val speed: Int // 速度 上不封顶
    val winWithSpeedDifferent: Int // 速度战胜难度乘数
    val avoidingBattle: Boolean // 避战, 为真时只能通过速度碾压战胜

    fun discover(): Boolean {
        // 首先你要发现
        // 感知 > 速度 * 隐蔽 / 最大隐蔽 / 大小 * 水桶大小
        // 因为最大隐蔽数值上与水桶大小相等, 因此消去
        return player.status[3] > speed * concealment / size
    }

    fun winWithSpeed(): Boolean {
        // 完全速度碾压
        // 灵巧 + 感知 / 2 > 速度 * 2
        return player.status[2] + player.status[3] / 2 > speed * winWithSpeedDifferent
    }

    fun win(): Boolean {
        // 常规方法取胜
        // 体质 + 意志 > 力量 * 攻击性 / 最大攻击性, 意思就是你扛得住伤害
        // 力量 * 体质 * 灵巧 * 感知 > 力量 * 速度 * 2, 意思就是你综合能力更强
        return avoidingBattle &&
                player.status[1] + player.status[5] > size * power * aggressiveness / 10 &&
                player.status[0] * player.status[1] * player.status[2] * player.status[3] > power * speed * 2
    }
}
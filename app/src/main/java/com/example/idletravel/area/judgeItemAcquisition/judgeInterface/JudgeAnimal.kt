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
        // 感知 > 速度 / 速度基准(人) * 隐蔽 / 最大隐蔽 / 大小 * 大小基准(水桶)
        // 因为最大隐蔽数值上与水桶大小相等, 因此消去
        return player.status[3] > speed / 10 * concealment / size
    }

    fun winWithSpeed(): Boolean {
        // 完全速度碾压
        // 灵巧 * 感知 / 2 > 速度 / 速度基准(人) * 速度战胜难度乘数
        return player.status[2] * player.status[3] / 2 > speed * winWithSpeedDifferent / 10
    }

    fun win(): Boolean {
        // 常规方法取胜
        // 体质 + 意志 > 力量 * 攻击性 / 最大攻击性, 意思就是你扛得住伤害
        // 力量 * 体质 * 灵巧 * 感知 > 力量 * 速度 * 2, 意思就是你综合能力更强
        return avoidingBattle &&
                player.status[1] + player.status[5] > power * aggressiveness / 10 &&
                player.status[0] * player.status[1] * player.status[2] * player.status[3] > power * speed * 2
    }
}

// 注意 数值非线性增加.

/*
    力量: 0~上不封顶
    0:  无力(字面意义)
    1:  微风吹拂
    10: 昆虫叮咬
    50: 小型动物啃咬
    150:家畜啃咬
    500:大型动物抓咬
*/

/*
    攻击性: 0~10
    0:  完全无害
    2:  被人类攻击后会反击
    4:  领地被入侵后会反击
    6:  无食物时会猎食人类
    8:  它们对吃人习以为常
    10: 它们以残虐人类为乐
*/

/*
    速度: 0~上不封顶
    0:  完全静止
    1:  起码它会动
    5:  人类行走 2m/s
    10: 人类跑步 5m/s
    40: 自行车 10m/s
    100:兔子 20m/s
*/

/*
    速度战胜难度乘数
    大小小于10 -> +1
    大小小于5 -> +1
    有洞穴 -> +2
*/
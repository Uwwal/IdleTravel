package com.example.idletravel.area.judgeItemAcquisition.judgeInterface

import com.example.idletravel.gameCalendar
import com.example.idletravel.gameCalender.Season

interface Judge {
    val size: Int // 大小 上不封顶
    val concealment: Int // 隐蔽 上限10
    val season: List<Season> // 出现季节

    val getJudgement: () -> Boolean

    fun judgeWithSeason(): Boolean {
        return gameCalendar.season in season
    }
}

// 注意 数值非线性增加.

/*
    大小: 0~上不封顶
    0:  无法观测
    1:  米粒大小
    2:  纽扣大小
    3:  瓶盖大小
    5:  水瓶大小
    10: 水桶大小 <- 发现难度基准
    30: 桌子大小
    100: 轿车大小
*/

/*
    隐蔽: 0~10
    0:  很难不让人注意到
    2:  近视眼摘掉眼镜看不见的程度
    4:  需要仔细端详
    6:  不动基本看不见
    8:  与环境融为了一体
    10: 雷达也无法发现的程度
*/
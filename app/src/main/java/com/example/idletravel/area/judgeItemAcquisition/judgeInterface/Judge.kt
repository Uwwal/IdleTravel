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

// TODO: 妈的 注释不知道怎么回事不见了
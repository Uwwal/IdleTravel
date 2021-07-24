package com.example.idletravel.gameCalender

import java.io.Serializable

class GameCalendar constructor(
    var year: Int = 1871,
    var season: Season = Season.SPRING,
    var time: Int = 0
) : Serializable {
    private val seasonTime = 300

    fun checkTime(): Boolean {
        var changed = false
        // 季节, 年份是否改变
        // 传到travel再由travel进行ui更新

        if (time >= seasonTime) {
            changed = true

            time -= seasonTime
            when (season) {
                Season.SPRING -> season = Season.SUMMER
                Season.SUMMER -> season = Season.AUTUMN
                Season.AUTUMN -> season = Season.WINTER
                Season.WINTER -> {
                    season = Season.SPRING
                    year++
                }
            }
        }

        return changed
    }

    fun getSeasonPhaseString(): String {
        // 孟 仲 季 指 季节的上中下三段
        return when {
            time > seasonTime / 3 * 2 -> {
                "孟"
            }
            time > seasonTime / 3 -> {
                "仲"
            }
            else -> {
                "伯"
            }
        }
    }
}
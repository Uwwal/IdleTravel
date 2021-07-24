package com.example.idletravel.gameCalender

import com.example.idletravel.format.INFORMATION_BLANK
import java.io.Serializable

enum class Season (val seasonName: String, val information: String): Serializable{
    SPRING(
        "春季",
        "${INFORMATION_BLANK}春季是一个温柔的季节, 万物欣欣向荣, 世界的魔力也开始逐渐充盈."
    ),
    SUMMER(
        "夏季",
        "${INFORMATION_BLANK}夏季魔力丰饶, 魔物也因此攻击性不强, 可以比较安心的远行采集物品了."
    ),
    AUTUMN(
        "秋季",
        "${INFORMATION_BLANK}秋季是人们撷取大地产出的季节. 丰收之后人们躲进城镇, 静待秋冬的结束."
    ),
    WINTER(
        "冬季",
        "${INFORMATION_BLANK}在冬季, 魔力水平的降低不仅使四处一片萧瑟, 还让一些凶恶的魔物四处游荡以寻找魔力来源."
    );
}
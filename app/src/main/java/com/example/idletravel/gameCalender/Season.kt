package com.example.idletravel.gameCalender

import java.io.Serializable

enum class Season (val seasonName: String): Serializable{
    SPRING("春季"),
    SUMMER("夏季"),
    AUTUMN("秋季"),
    WINTER("冬季");
}
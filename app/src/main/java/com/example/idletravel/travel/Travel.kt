package com.example.idletravel.travel

import android.os.Handler
import com.example.idletravel.area.Area
import com.example.idletravel.customItem.CustomItem


class Travel constructor(
    private val area: Area,
    private val travelList: MutableList<Area>, // 旅行计划
    private val item: MutableMap<CustomItem, Int>,
) {
    private var inTravel: Boolean = false // 旅行中
    private var time: Int = 0
    private val handler = Handler()
    private val runnable = Runnable {
        val area = this.travelList[0]

        if (time < area.travelTime) {
            time++

            checkDrop(area)

            travel()
        } else {
            // 这里有个旅行时间 == time的边界情况, 所以要检查
            checkDrop(area)

            finishTravel()
        }

    }

    private fun checkDrop(area: Area) {
        if (area.checkDropsLocation(time)) {
            val dropItem: CustomItem = area.getDrops()

            var value = this.item[dropItem]
            if (value == null) {
                value = 1
            }
            this.item.replace(dropItem, value)
        }
    }


    private fun travel() {
        inTravel = true
        // 每一秒都会进行一次旅行
        handler.postDelayed(runnable, 1000)
    }

    fun addTravelPlan() {
        // 外界的入口
        travelList.add(area)
        if (!inTravel) {
            travel()
        }
    }

    private fun finishTravel() {
        // 结束旅行
        handler.removeCallbacks(runnable)
        time = 0
        inTravel = false
        travelList.removeAt(0)
        if (travelList.size > 0) {
            // 如果旅行列表非空则继续旅行
            travel()
        }
    }
}
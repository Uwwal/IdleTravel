package com.example.idletravel.travel

import android.os.Handler
import com.example.idletravel.StartGame
import com.example.idletravel.area.Area
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.itemMap


class Travel constructor(
    private val area: Area,
    private val travelList: MutableList<Area>, // 旅行计划
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
            finishTravel()
        }

    }

    private fun checkDrop(area: Area) {
        if (area.checkDropsLocation(time)) {
            val dropItem: CustomItem = area.getDrops()

            val value = itemMap[dropItem]
            if (value == null) {
                itemMap[dropItem] = 1
            }else{
                itemMap.replace(dropItem, value+1)
            }

            // TODO: 接个更新物品ui操作
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
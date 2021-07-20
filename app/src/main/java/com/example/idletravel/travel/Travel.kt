package com.example.idletravel.travel

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.idletravel.StartGame
import com.example.idletravel.area.Area
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.itemMap
import com.example.idletravel.itemViewMap


class Travel(
    private val context: StartGame
) {
    private val travelList: MutableList<Area> = ArrayList() // 旅行计划
    private var inTravel: Boolean = false // 旅行中
    private var time: Int = 0
    private val handler = Handler()
    private val runnable = Runnable {
        val area = this.travelList[0]

        if (time < area.travelTime) {
            time++

            checkDrop(area)

            travel()
        }

        if (time == area.travelTime) {
            finishTravel()
        }

    }

    private fun checkDrop(area: Area) {
        if (area.checkDropsLocation(time)) {
            val dropItem: CustomItem = area.getDrops()

            var value = itemMap[dropItem]
            if (value == null) {
                value = 1
                itemMap[dropItem] = 1
            } else {
                value ++
                itemMap.replace(dropItem, value)
            }

            // 更新ui操作
            val itemViewList = itemViewMap[dropItem]
            if (itemViewList == null) {
                context.createItemView(dropItem, value)
            } else {
                itemViewList.itemCountTextView.text = value.toString()
            }
        }
    }


    private fun travel() {
        inTravel = true
        // 每一秒都会进行一次旅行
        handler.postDelayed(runnable, 1000)
    }

    fun addTravelPlan(area: Area) {
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
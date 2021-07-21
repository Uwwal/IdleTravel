package com.example.idletravel.travel

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.idletravel.*
import com.example.idletravel.area.Area
import com.example.idletravel.customItem.CustomItem

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
        val name = player.name
        if (area.checkDropsLocation(time)) {
            val dropItem: CustomItem = area.getDrops()

            var value = itemMap[dropItem]
            if (value == null) {
                value = 1
                itemMap[dropItem] = 1
            } else {
                value++
                itemMap.replace(dropItem, value)
            }

            context.createTravelLogView(name + "在" + area.name + "找到了一个" + dropItem.name + ".")

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
        val name = player.name
        travelList.add(area)

        context.createTravelLogView(name + "已经将" + area.name + "加入到旅行计划里了!")

        context.createTravelListView(area)

        if (!inTravel) {
            travel()
        }
    }

    private fun finishTravel() {
        val name = player.name

        // 结束旅行
        handler.removeCallbacks(runnable)
        time = 0
        inTravel = false

        context.createTravelLogView(name + "刚刚在" + travelList[0].name + "旅行完了.")

        context.removeTravelButton(travelListButtonList[0])

        travelList.removeAt(0)


        context.createTravelLogView(name + "正在查看下一个旅行计划...")

        if (travelList.isNotEmpty()) {
            // 如果旅行列表非空则继续旅行
            context.createTravelLogView(name + "即将前往" + travelList[0].name + ".")
            travel()
        } else {
            context.createTravelLogView(name + "完成了目前所有的计划, 开心!")
        }
    }
}
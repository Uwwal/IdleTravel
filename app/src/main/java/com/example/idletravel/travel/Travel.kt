package com.example.idletravel.travel

import android.os.Handler
import com.example.idletravel.*
import com.example.idletravel.area.CustomArea
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.format.CustomColor
import com.example.idletravel.format.formatStringWithColor
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class Travel(
    private val context: StartGameActivity
) {
    val travelList: MutableList<CustomArea> = ArrayList() // 旅行计划
    private var inTravel: Boolean = false // 旅行中
    private var time: Int = 0 // 当前旅行时间 = 当前旅行位置 一个意思 注意这是从0开始的
    private val handler = Handler()
    private val runnable = Runnable {
        // 循环主体
        val area = this.travelList[0]

        if (time < area.travelTime) {
            // 注意这个先判断而后再增
            // 假如travelTime是5的话, 这里执行5次, 第6次执行finishTravel()
            time++

            // 判断季节是否更新
            gameCalendar.time++
            if (gameCalendar.checkTime()) {
                context.updateCalendarTextView()
            }

            checkDrop(area)

            travel()
        }

        if (time == area.travelTime) {
            finishTravel()
        }
    }

    private fun checkDrop(area: CustomArea) {
        // 检查掉落物
        val playerName = player.name
        val itemMap = context.itemCountMap
        if (area.checkDropsLocation(time)) {
            // 进行了检查掉落物位置
            val dropItem: CustomItem = area.getDrops()
            val name = dropItem.name

            var itemCount = itemMap[dropItem.name]
            if (itemCount == null) {
                itemCount = 1
                itemMap[name] = 1
            } else {
                itemCount++
                itemMap.replace(name, itemCount)
            }


            val dateFormat: DateFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.CHINA)
            val timeCurrent: String = dateFormat.format(Date())

            context.createTravelLogView(
                formatStringWithColor(
                    listOf(
                        "[$timeCurrent]: ${playerName}在${area.areaName}找到了一个",
                        dropItem.itemName,
                        "."
                    ), listOf(
                        CustomColor.DEFAULT.colorHEX,
                        dropItem.itemColor,
                        CustomColor.DEFAULT.colorHEX
                    )
                )
            )

            // 更新物品ui操作
            val itemCountTextView = context.itemCountTextViewMap[name]
            if (itemCountTextView == null) {
                context.createItemView(dropItem, itemCount)
            } else {
                itemCountTextView.text = itemCount.toString()
            }

            // 奖励点属性
            val randStatusIndex = (0 until player.status.size).random()
            player.status[randStatusIndex] += bonusStatus(player.status[randStatusIndex], area)
            context.updatePlayerStatusTextView()
        }
    }

    private fun bonusStatus(status: Double, area: CustomArea): Double {
        if (status > area.lowerLimitOfBonusStatus && status < area.upperLimitOfBonusStatus) {
            val finishTime: Int = player.finishMapTime[area.areaName] ?: 0

            return (1 / (finishTime + 5.0) * (area.travelTime / area.dropsNumber.toDouble()) + area.travelTime / 600) / 10
            // 600是最大旅行时间
            // 这里必须5.0来返回Double
        }
        return 0.0
    }

    private fun travel() {
        inTravel = true
        // 每一秒都会进行一次旅行

        handler.postDelayed(runnable, 1000)
    }

    fun addTravelPlan(area: CustomArea) {
        // 外界的入口
        val name = player.name
        travelList.add(area)

        context.createTravelLogView("${name}已经将${area.areaName}加入到旅行计划里了!")

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

        val finishTime: Int? = player.finishMapTime[travelList[0].name]
        player.finishMapTime[travelList[0].name] = if (finishTime == null) {
            1
        } else {
            finishTime + 1
        }

        context.createTravelLogView("${name}刚刚在${travelList[0].areaName}旅行完了.")

        context.removeTravelListButton(context.travelListButtonList[0])
        // 这里进行了travelList.removeAt

        context.createTravelLogView("${name}正在查看下一个旅行计划...")

        if (travelList.isNotEmpty()) {
            // 如果旅行列表非空则继续旅行
            context.createTravelLogView("${name}即将前往${travelList[0].areaName}.")
            travel()
        } else {
            context.createTravelLogView("${name}完成了目前所有的计划, 开心!")
        }
    }

}
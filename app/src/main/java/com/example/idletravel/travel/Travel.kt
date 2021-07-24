package com.example.idletravel.travel

import android.os.Handler
import com.example.idletravel.*
import com.example.idletravel.area.Area
import com.example.idletravel.customItem.CustomItem

class Travel(
    private val context: StartGameActivity
) {
    val travelList: MutableList<Area> = ArrayList() // 旅行计划
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

            checkDrop(area)

            travel()
        }

        if (time == area.travelTime) {
            finishTravel()
        }
    }

    private fun checkDrop(area: Area) {
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

            context.createTravelLogView("${playerName}在${area.name}找到了一个${dropItem.itemName}.")

            // 更新物品ui操作
            val itemCountTextView = context.itemCountTextViewMap[name]
            if (itemCountTextView == null) {
                context.createItemView(dropItem, itemCount)
            } else {
                itemCountTextView.text = itemCount.toString()
            }

            // 奖励点属性
            val randStatusIndex = (Math.random() * player.status.size).toInt()
            player.status[randStatusIndex] += bonusStatus(player.status[randStatusIndex], area)
            context.updatePlayerStatusTextView()
        }
    }

    private fun bonusStatus(status: Double, area: Area): Double {
        if (status > area.lowerLimitOfBonusStatus && status < area.upperLimitOfBonusStatus) {
            var finishTime: Int? = player.finishMapTime[area]
            if (finishTime == null) {
                finishTime = 0
            }

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

    fun addTravelPlan(area: Area) {
        // 外界的入口
        val name = player.name
        travelList.add(area)

        context.createTravelLogView("${name}已经将${area.name}加入到旅行计划里了!")

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

        val finishTime: Int? = player.finishMapTime[travelList[0]]
        player.finishMapTime[travelList[0]] = if (finishTime == null) {
            1
        } else {
            finishTime + 1
        }

        context.createTravelLogView("${name}刚刚在${travelList[0].name}旅行完了.")

        context.removeTravelListButton(context.travelListButtonList[0])
        // 这里进行了travelList.removeAt

        context.createTravelLogView("${name}正在查看下一个旅行计划...")

        if (travelList.isNotEmpty()) {
            // 如果旅行列表非空则继续旅行
            context.createTravelLogView("${name}即将前往${travelList[0].name}.")
            travel()
        } else {
            context.createTravelLogView("${name}完成了目前所有的计划, 开心!")
        }
    }

}
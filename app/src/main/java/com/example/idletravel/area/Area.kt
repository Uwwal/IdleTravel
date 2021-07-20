package com.example.idletravel.area

import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.customItem.items.garbageItem

class Area constructor(
    val name: String,
    val information: String,
    val travelTime: Int, // 旅行时间
    private val dropsMap: Map<CustomItem, () -> Boolean>, // 掉落物检查表, value值是个lambda
    private val dropsNumber: Int, // 掉落量
    private val dropsWeightList: List<Int>, // 掉落物权重
    private val dropsList: List<CustomItem> // 掉落物列表 和掉落物权重index相同
) {
    private var totalWeight = this.sumWeight() //总权重
    private var dropsLocationList = this.getDropsLocation()

    private fun sumWeight(): Int {
        var j = 0
        for (i in dropsWeightList) {
            j += i
        }
        return j
    }

    private fun checkDrops(item: CustomItem): Boolean? {
        return this.dropsMap[item]?.invoke()
    }

    private fun getDropsLocation(): MutableList<Int> {
        // 获取掉落物位置列表
        // 会返回一个[1,travelTime]的列表, size为掉落物数量
        val temList: MutableList<Int> = ArrayList()
        val originList: MutableList<Int> = ArrayList()

        for (i in 1..this.travelTime) {
            originList.add(i)
        }

        for (i in 1..this.dropsNumber) {
            val rand = (Math.random() * originList.size).toInt()
            temList.add(originList[rand])
            originList.removeAt(rand)
        }
        temList.sort()
        return temList
    }

    fun getDrops(): CustomItem {
        // 根据权重获取掉落物
        // [1,2,3] -> 1: [1,2) 2: [2,4) 3:[4,7)
        val rand: Int = (Math.random() * (this.totalWeight + 1)).toInt() + 1
        var leftLimit = dropsWeightList[0]
        var rightLimit = leftLimit
        var drop: CustomItem = garbageItem

        for (i in dropsWeightList.indices) {
            leftLimit = rightLimit
            rightLimit += dropsWeightList[i]

            if (rand in leftLimit until rightLimit) {
                drop = dropsList[i]
                break
            }
        }
        return if (checkDrops(drop) == true) {
            drop
        } else {
            garbageItem
        }
    }

    fun checkDropsLocation(locationCurrent: Int): Boolean {
        //TODO: 我草这里有bug
        // 数组溢出

        if (locationCurrent == this.dropsLocationList[0]) {
            this.dropsLocationList.removeAt(0)

            reloadDropsLocationList(locationCurrent)

            return true
        }
        return false
    }

    private fun reloadDropsLocationList(locationCurrent: Int) {
        if (dropsLocationList.isEmpty() && locationCurrent == travelTime) {
            dropsLocationList = getDropsLocation()
        }
    }

}
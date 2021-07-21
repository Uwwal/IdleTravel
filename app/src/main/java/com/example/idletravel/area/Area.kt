package com.example.idletravel.area

import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.customItem.items.garbageItem
import com.example.idletravel.format.areaInformationBlank

class Area constructor(
    val name: String,
    var information: String,
    val travelTime: Int, // 旅行时间
    private val dropsMap: Map<CustomItem, () -> Boolean>, // 掉落物检查表, value值是个lambda
    private val dropsNumber: Int, // 掉落量
    private val dropsWeightList: List<Int>, // 掉落物权重
    private val dropsList: List<CustomItem> // 掉落物列表 和掉落物权重index相同
) {
    init {
        // 添加段落开头空格
        information = areaInformationBlank + information
    }

    private var totalWeight = this.sumWeight() //总权重
    private var dropsLocationList = this.getDropsLocation()
    private var finish = false //完成标记

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
        if (!finish) {
            if (locationCurrent == this.dropsLocationList[0]) {
                this.dropsLocationList.removeAt(0)

                if (dropsLocationList.isEmpty()) {
                    // 防止数组溢出
                    finish = true
                }

                reload(locationCurrent)

                return true
            }
        }
        reload(locationCurrent)

        return false
    }

    private fun reload(locationCurrent: Int) {
        // 旅行完毕重新加载一组新的掉落位置和完成状态
        if (locationCurrent == travelTime) {
            dropsLocationList = getDropsLocation()
            finish = false
        }
    }

}
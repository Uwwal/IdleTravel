package com.example.idletravel.area

import com.example.idletravel.area.maps.lawnDropsList
import com.example.idletravel.area.maps.lawnDropsMap
import com.example.idletravel.area.maps.lawnDropsWeightList
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.format.INFORMATION_BLANK
import java.io.Serializable

enum class CustomArea(
    val areaName: String,
    var information: String,
    val travelTime: Int, // 旅行时间 设想是从5到600
    private val dropsMap: HashMap<String, () -> Boolean>, // 掉落物检查表, value值是个lambda
    val dropsNumber: Int, // 掉落量 设想是从3到100
    private val dropsWeightList: List<Int>, // 掉落物权重
    private val dropsList: List<String>, // 掉落物列表 和掉落物权重index相同
    val upperLimitOfBonusStatus: Int = 9, // 奖励属性上限值
    val lowerLimitOfBonusStatus: Int = 0, // 下限值
) : Serializable {
    LAWN(
        "草地",
        "家门口的草地, 与其说是旅行不如说是散步...\n" +
                INFORMATION_BLANK + "稀稀疏疏的分布着一些灌木, 地上散落着没素质的路人留下的垃圾. 总之就是很乏味啦.\n" +
                INFORMATION_BLANK + "当然你如果想拒绝乏味的话也可以考虑和昆虫, 兔子之类的斗智斗勇.. 很要求技术就是了.",
        5,
        lawnDropsMap,
        3,
        lawnDropsWeightList,
        lawnDropsList,
        9,
        0
    )
    ;
    private var finish: Boolean = false
    private var totalWeight = this.sumWeight() // 总权重
    private var dropsLocationList = this.getDropsLocation()

    init {
        information = INFORMATION_BLANK + information
    }

    private fun sumWeight(): Int {
        // 获取总权重
        var j = 0
        for (i in dropsWeightList) {
            j += i
        }
        return j
    }

    private fun checkDrops(item: String): Boolean? {
        // 这是个调用lambda看是否符合要求
        return this.dropsMap[item]?.invoke()
    }

    private fun getDropsLocation(): MutableList<Int> {
        // 获取掉落物位置列表
        // 会返回一个[1,travelTime]的列表, size为掉落物数量
        var temList: MutableList<Int> = MutableList(travelTime){it}

        temList.shuffle()
        temList = temList.take(dropsNumber) as MutableList<Int>

        // 排序为了按顺序获取, 因为time是+1 判断 +1 判断
        temList.sort()
        return temList
    }

    fun getDrops(): CustomItem {
        // 根据权重获取掉落物
        // [1,2,3] -> 1: [1,2) 2: [2,4) 3:[4,7)
        // 你可以想象这是一个数轴, 随机数在数轴上对应范围结算掉落物
        val rand: Int = (1 until (this.totalWeight + 1)).random()
        var leftLimit: Int
        var rightLimit = 0
        var drop: CustomItem = CustomItem.GARBAGE

        for (i in dropsWeightList.indices) {
            leftLimit = if (i == 0) {
                1
            } else {
                rightLimit
            }
            rightLimit += dropsWeightList[i]

            if (rand in leftLimit until rightLimit) {
                drop = CustomItem.valueOf(dropsList[i])
                break
            }
        }

        // 不符合条件就返回个垃圾
        return if (checkDrops(drop.name) == true) {
            drop
        } else {
            CustomItem.GARBAGE
        }
    }

    fun checkDropsLocation(locationCurrent: Int): Boolean {
        // 未完成->检测当前位置是否与掉落位置相同, 相同则返回true
        if (!finish) {
            if (locationCurrent == this.dropsLocationList[0]) {
                this.dropsLocationList.removeAt(0)

                if (dropsLocationList.isEmpty()) {
                    // 防止数组溢出
                    finish = true
                }

                // 边界情况1: 完成时恰好旅行完, 顺便重新获取
                determiningReloadDropsLocationList(locationCurrent)

                return true
            }
        }
        // 完成则检测是否需要重新获取
        determiningReloadDropsLocationList(locationCurrent)

        return false
    }

    private fun determiningReloadDropsLocationList(locationCurrent: Int) {
        // 旅行完毕重新加载一组新的掉落位置和完成状态
        // 注意这里判断了是否time等于完成旅行时间
        if (locationCurrent == travelTime) {
            dropsLocationList = getDropsLocation()
            finish = false
        }
    }
}
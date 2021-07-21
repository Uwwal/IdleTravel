package com.example.idletravel.area.maps

import com.example.idletravel.area.Area
import com.example.idletravel.area.areaInformationBlank
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.customItem.items.garbageItem
import com.example.idletravel.customItem.items.softTwigsItem

private val dropsList = listOf(garbageItem, softTwigsItem)
private val dropsMap: Map<CustomItem, () -> Boolean> =
    mapOf(garbageItem to { true }, softTwigsItem to { true })
private val dropsWeightList = listOf(2, 1)

val grasslandArea = Area(
    "草地",
    areaInformationBlank+ "家门口的草地, 与其说是旅行不如说是散步...\n"+
                areaInformationBlank+ "稀稀疏疏的分布着一些灌木, 地上散落着没素质的路人留下的垃圾.",
    5,
    dropsMap,
    3,
    dropsWeightList,
    dropsList
)

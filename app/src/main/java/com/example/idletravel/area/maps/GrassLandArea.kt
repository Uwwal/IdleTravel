package com.example.idletravel.area.maps

import com.example.idletravel.area.Area
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.customItem.items.garbageItem
import com.example.idletravel.customItem.items.softTwigsItem

private val dropsList = listOf(garbageItem, softTwigsItem)
private val dropsMap: Map<CustomItem, () -> Boolean> =
    mapOf(garbageItem to { true }, softTwigsItem to { true })
private val dropsWeightList = listOf(2, 1)

val grasslandArea = Area(
    "草地",
    "啊嗯草地啊, 就像wym滚过的一样, 香香的, 可爱的...",
    5,
    dropsMap,
    3,
    dropsWeightList,
    dropsList
)

package com.example.idletravel.customItem

import java.io.Serializable

class ItemMap constructor(
    var map: MutableMap<CustomItem, Int>
): Serializable {
}
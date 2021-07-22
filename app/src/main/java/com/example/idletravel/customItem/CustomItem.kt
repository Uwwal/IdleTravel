package com.example.idletravel.customItem

import com.example.idletravel.format.areaInformationBlank
import java.io.Serializable

class CustomItem constructor(
    var information:String,
    val name:String
): Serializable{
    init {
        information = areaInformationBlank+information
    }
}
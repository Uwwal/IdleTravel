package com.example.idletravel.customItem

import com.example.idletravel.format.areaInformationBlank

class CustomItem constructor(
    var information:String,
    val name:String
){
    init {
        information = areaInformationBlank+information
    }
}
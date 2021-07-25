package com.example.idletravel.production

import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.format.INFORMATION_BLANK

enum class CustomProduction(
    override val materialNumberMap: Map<String, Int>,
    override val recipe: List<String> = listOf(noRecipeRequired),
    override val production: String,
    override val produceName: String,
    override var productionInformation: String,
) : Production {
    GARBAGE_EXTRACTION(
        mapOf(CustomItem.GARBAGE.name to 5),
        listOf(noRecipeRequired),
        getRandomItem(),
        "废物提取",
        "test"
    )
    ;

    init {
        productionInformation = INFORMATION_BLANK + productionInformation
    }

    override val getProduct: () -> String = {
        // TODO: 完善方法, 还有属性要求.
    }
}

private fun getRandomItem(): String {
    val array = CustomItem.values()
    array.shuffle()
    return array[0].name
}
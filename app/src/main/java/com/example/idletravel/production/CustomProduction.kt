package com.example.idletravel.production

import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.format.INFORMATION_BLANK
import com.example.idletravel.itemCountMap

enum class CustomProduction(
    override val materialNumberMap: Map<String, Int>,
    override val statusMap: Map<String, Int>,
    override val recipe: List<String> = listOf(noRecipeRequired),
    override val production: String = CustomItem.GARBAGE.name,
    override val produceName: String,
    override var productionInformation: String,
) : Production {
    GARBAGE_EXTRACTION(
        mapOf(CustomItem.GARBAGE.name to 5),
        mapOf("学识" to 1),
        listOf(noRecipeRequired),
        produceName="废物提取",
        productionInformation = "test"
    ){
        override val getProduct: () -> String = {
            // 重载为了每次随机物品不同
            var produce:String = CustomItem.GARBAGE.name

            if(checkRecipe()&&checkStatus()&&checkMaterial()){
                produce = getRandomItem()
            }

            produce
        }
    }
    ;


    init {
        productionInformation = INFORMATION_BLANK + productionInformation
    }

    override val getProduct: () -> String = {
        // ui更新在调用的类里执行
        var produce:String = CustomItem.GARBAGE.name

        if(checkRecipe()&&checkStatus()&&checkMaterial()){
            produce = produceName

            for ((key, value) in materialNumberMap) {
                // 这里就要减 因为外部不知道执行是否成功
                var itemCount = itemCountMap[key]
                if (itemCount != null) {
                    itemCount -= value
                    itemCountMap.replace(key,value)
                }
            }
        }

        produce
    }
}

private fun getRandomItem(): String {
    val array = CustomItem.values()
    array.shuffle()
    return array[0].name
}
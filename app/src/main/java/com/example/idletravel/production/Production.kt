package com.example.idletravel.production

import android.text.Spanned
import com.example.idletravel.customItem.CustomItem
import com.example.idletravel.format.CustomColor
import com.example.idletravel.format.INFORMATION_BLANK
import com.example.idletravel.format.formatStringWithColor
import com.example.idletravel.itemCountMap

interface Production {
    val materialNumberMap: Map<String, Int>
    val recipe: List<String>
    val production: String
    val produceName: String
    val productionInformation: String

    val getProduct: () -> String

    fun getInformation(): Spanned {
        val textList: MutableList<String> = mutableListOf()
        val colorList: MutableList<String> = mutableListOf()

        textList.add("合成信息:\n${productionInformation}\n原料需求:\n")
        colorList.add(CustomColor.DEFAULT.colorHEX)

        for ((key, value) in materialNumberMap) {
            textList.add(getItemName(key, value))
            colorList.add(CustomItem.valueOf(key).itemColor)
        }

        textList.add("配方需求:\n")
        if (recipe[0] == noRecipeRequired) {
            textList.add(noRecipeRequired)
            colorList.add(CustomColor.DEFAULT.colorHEX)
        } else {
            for (i in recipe) {
                textList.add(getItemName(i))
                colorList.add(CustomItem.valueOf(i).itemColor)
            }
        }
        return formatStringWithColor(textList,colorList)
    }

    private fun getItemName(
        key: String,
        value: Int
    ) = "${INFORMATION_BLANK}${CustomItem.valueOf(key).itemName}: $value\n"

    private fun getItemName(
        key: String,
    ) = "${INFORMATION_BLANK}${CustomItem.valueOf(key).itemName}\n"

    fun checkRecipe(recipe: List<String>): Boolean {
        if (recipe[0] == noRecipeRequired) {
            return true
        }

        for (i in recipe) {
            if (itemCountMap[i] == null || itemCountMap[i] == 0) {
                return false
            }
        }

        return true
    }
}

const val noRecipeRequired: String = "不需要配方和仪器"

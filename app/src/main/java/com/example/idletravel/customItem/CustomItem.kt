package com.example.idletravel.customItem

import com.example.idletravel.format.CustomColor
import com.example.idletravel.format.INFORMATION_BLANK
import java.io.Serializable

enum class CustomItem(
    var information:String,
    val itemName:String,
    val itemColor: String,
):Serializable{
    GARBAGE(
        "虽说是垃圾, 但是其中有好东西也不一定, 只是因为乱糟糟的让人没有翻捡的意愿啊.",
        "垃圾",
        CustomColor.ITEM_COMMON.colorHEX
    ),
    SOFT_TWIG(
        "柔弱, 鲜嫩的细枝条. 想好好的保存不使其弯折也是一种技术活.",
        "细枝",
        CustomColor.ITEM_COMMON.colorHEX
    ),
    BERRY(
        "小小的亮红色浆果, 很酸...",
        "浆果",
        CustomColor.ITEM_COMMON.colorHEX
    ),
    LADYBUG(
        "普通的瓢虫, 捉起来并不费劲.",
        "瓢虫",
        CustomColor.ITEM_COMMON.colorHEX
    ),
    LONG_HORNED_BEETLE(
        "大大的天牛, 被夹一下会很疼.",
        "天牛",
        CustomColor.ITEM_COMMON.colorHEX
    ),
    ANT(
        "勤劳又数量繁多的家伙.",
        "蚂蚁",
        CustomColor.ITEM_COMMON.colorHEX
    ),
    RABBIT(
        "在草地上四处打洞的坏蛋.",
        "兔子",
        CustomColor.ITEM_UNCOMMON.colorHEX
    )
    ;
    init {
        information = INFORMATION_BLANK+information
    }
}
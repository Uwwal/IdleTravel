package com.example.idletravel.format

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

fun formatView(
    view: View,
    params: ViewGroup.LayoutParams,
    visibility: Int = View.GONE
): View {
    view.layoutParams = params
    view.visibility = visibility
    return view
}


fun formatTextView(
    view: TextView,
    params: ViewGroup.LayoutParams,
    visibility: Int = View.GONE,
    text: String? = null,
    textSize: Float = 0F
): TextView {
    val tem = formatView(view, params, visibility) as TextView
    tem.text = text
    tem.textSize = textSize
    return tem
}


fun formatButton(
    view: Button,
    params: ViewGroup.LayoutParams,
    visibility: Int = View.GONE,
    text: String? = null,
    textSize: Float = 0F
): Button {
    val tem = formatView(view, params, visibility) as Button
    tem.text = text
    tem.textSize = textSize
    return tem
}

val formatPlayerStatusTextTwoLines: (List<Int>) -> String = { status ->
    "   力量: " + status[0] + "   体质: " + status[1] +
            "   灵巧: " + status[2] + "   感知: " + status[3] + "\n" +
            "   学识: " + status[4] + "   意志: " + status[5] +
            "   魔力: " + status[6] + "   魅力: " + status[7]
}


val formatPlayerStatusTextEightLines: (List<Int>) -> String = { status ->
    "力量:   " + status[0] + "\n" +
            "体质:   " + status[1] + "\n" +
            "灵巧:   " + status[2] + "\n" +
            "感知:   " + status[3] + "\n" +
            "学识:   " + status[4] + "\n" +
            "意志:   " + status[5] + "\n" +
            "魔力:   " + status[6] + "\n" +
            "魅力:   " + status[7]
}


const val areaInformationBlank = "        "
// 段落开头的空格占位
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

val formatPlayerStatusTextTwoLines: (List<Double>) -> String = { status ->
    String.format(
        " 力量: %.0f   体质: %.0f   灵巧: %.0f   感知: %.0f\n" +
                " 学识: %.0f   意志: %.0f   魔力: %.0f   魅力: %.0f",
        status[0], status[1], status[2], status[3], status[4], status[5], status[6], status[7]
    )
}


val formatPlayerStatusTextEightLines: (List<Double>) -> String = { status ->
    String.format(
        "力量:   %.2f\n体质:   %.2f\n灵巧:   %.2f\n感知:   %.2f\n" +
                "学识:   %.2f\n意志:   %.2f\n魔力:   %.2f\n魅力:   %.2f",
        status[0], status[1], status[2], status[3], status[4], status[5], status[6], status[7]
    )
}

const val informationBlank = "        "
// 段落开头的空格占位
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
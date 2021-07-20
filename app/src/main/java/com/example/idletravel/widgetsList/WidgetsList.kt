package com.example.idletravel.widgetsList

import android.text.Layout
import android.view.View
import android.widget.LinearLayout

class WidgetsList constructor(){
    var widgetsList:MutableList<View> = ArrayList() // 组件列表

    fun changeMainViewContent(mainView:LinearLayout) {
        for (i in 0 until mainView.childCount){
            // 所有子组件不可见
            mainView.getChildAt(i).visibility=View.GONE
        }
        for (element in this.widgetsList) {
            // 对应组件列表可见
            element.visibility = View.VISIBLE
        }
    }
}
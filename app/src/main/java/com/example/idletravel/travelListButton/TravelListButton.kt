package com.example.idletravel.travelListButton

import android.content.Context
import androidx.appcompat.widget.AppCompatButton


class TravelListButton(context: Context) : AppCompatButton(context) {
    var index:Int = 0
}

// Button带了个index属性, 用来获取在父视图中位置的.
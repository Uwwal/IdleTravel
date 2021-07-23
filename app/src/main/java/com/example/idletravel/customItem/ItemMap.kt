package com.example.idletravel.customItem

import java.io.Serializable

class ItemMap constructor(
    var map: MutableMap<String, Int>
): Serializable {
}

// 这个类用来在两个activity之间传递Map
// 注意反序列化之后自定义类的对象变成了新的对象
// 因此这里用string
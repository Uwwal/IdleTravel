package com.example.idletravel.transmissionMap

import java.io.Serializable

class TransmissionMap(var map: MutableMap<String, Int>) : Serializable {
}

// 这个类用来在两个activity之间传递Map
// 注意反序列化之后自定义类的对象变成了新的对象
// 因此这里用string
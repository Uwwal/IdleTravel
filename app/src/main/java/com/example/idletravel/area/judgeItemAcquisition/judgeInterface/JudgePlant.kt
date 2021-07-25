package com.example.idletravel.area.judgeItemAcquisition.judgeInterface

import com.example.idletravel.player

interface JudgePlant : Judge{
    val discoverDifficult: Int

    override fun discover(): Boolean {
        // 首先你要发现
        // 感知 > 速度 / 速度基准(人) * 隐蔽 / 最大隐蔽 / 大小 * 大小基准(水桶)
        // 因为最大隐蔽数值上与水桶大小相等, 因此消去
        return player.getStatus("感知") > 1 // TODO: 等待修订
    }
}
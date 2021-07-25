package com.example.idletravel.area.judgeItemAcquisition.judgeInterface

import com.example.idletravel.player

interface JudgePlant : Judge{
    val discoverDifficulty: Int // 发现难度乘数
    val pickingDifficulty: Int // 采摘难度乘数
    val camouflageAbility: Int // 伪装, 上不封顶
    val distinguishDifficult: Int // 辨识难度乘数

    override fun discover(): Boolean {
        // 首先你要发现
        // 感知 > 隐蔽 / 最大隐蔽 / 大小 * 大小基准(水桶) * 发现难度乘数
        // 因为最大隐蔽数值上与水桶大小相等, 因此消去
        return player.getStatus("感知") > discoverDifficulty * concealment / size
    }

    fun pick(): Boolean{
        // 采摘判定
        // 灵巧 > 采摘难度乘数 / 大小 * 大小基准(苹果)
        return player.getStatus("灵巧") >  pickingDifficulty * 4 / size
    }

    fun distinguish(): Boolean{
        // 成功辨识出自然造物属于此种类
        return player.getStatus("学识") > camouflageAbility * distinguishDifficult
    }
}

// 注意 乘数只加不减

/*
    发现难度乘数:
        枝繁叶茂 -> +1
        size<3 -> +1
        环境颜色相近 -> +1
*/

/*
    采摘难度乘数:
        有刺 -> +1
        有小型生物保护 -> +1
        采摘后会使地形改变 -> +2
        柔弱的 -> +1
        生长在树上 -> +1
*/

/*
    伪装:
        0:  简直独一无二
        1:  小孩都知道怎么分辨这个
        3:  生活中绝大部分常识在这里依然适用
        10: 生活中部分常识在这里依然适用
        50: 需要植物领域有一定了解才能辨识出
        100:有人专以分辨这种物品为生
        1000:   领域中的人博览会上这种物品是不是真的吵个不停
        10000:  很多人尝试找出分辨这个物品的方法
*/

/*
    辨识难度乘数:
        需要一定生活常识 -> +1
        外人无法想象的, 反常理的分辨手段 -> +2
        通过特殊复杂的纹路走向分辨 -> +1
*/
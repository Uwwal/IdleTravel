package com.example.idletravel.format

import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.text.Spanned
import com.example.idletravel.gameCalender.GameCalendar
import com.example.idletravel.player.Player

fun formatPlayerStatusText(
    lineCount: Int,
    formatter: (Pair<String, Double>) -> String
): (List<Double>) -> String = { statusList ->
    val len = Player.statusNames.size / lineCount
    (Player.statusNames zip statusList)
        .map(formatter)
        .chunked(len)
        .map { it.joinToString("   ") }
        .joinToString("\n")
}

val formatPlayerStatusTextTwoLines =
    formatPlayerStatusText(2) { (name, value) -> "${name}: %.0f".format(value) }

val formatPlayerStatusTextEightLines =
    formatPlayerStatusText(8) { (name, value) -> "${name}:   %.2f".format(value) }

val formatGameCalendarToTime: (GameCalendar) -> String = { gameCalendar ->
    "" + gameCalendar.year + "年 " + gameCalendar.getSeasonPhaseString() + gameCalendar.season.seasonName[0]
}

val formatStringWithColor: (List<String>, List<String>) -> Spanned = { stringList, colorList ->
    val string = (colorList zip stringList)
        .joinToString { (color, str) -> "<font color=${color}>${str}</font>" }
    Html.fromHtml(string, FROM_HTML_MODE_COMPACT)
}

const val INFORMATION_BLANK = "　　"
// 段落开头的空格占位
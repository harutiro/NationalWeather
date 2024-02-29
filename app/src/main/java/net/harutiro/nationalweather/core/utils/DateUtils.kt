package net.harutiro.nationalweather.core.utils

import java.text.SimpleDateFormat
import java.util.Date

class DateUtils {
    companion object{
        fun getNowString(): String {
            //　フォーマットを　MM月dd日 (曜日)　にする
            val date = Date()
            val format = SimpleDateFormat("MM月dd日 (E)" , java.util.Locale.JAPAN)
            return format.format(date)
        }
    }
}
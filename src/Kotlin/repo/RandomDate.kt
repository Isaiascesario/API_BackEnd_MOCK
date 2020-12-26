package com.cunha.isaias.backend.mock.Kotlin.repo

import java.sql.Timestamp
import kotlin.random.Random


class RandomDate {
    fun generateRandomDate(ano: Int, mes: Int,ind : Int):Long{
        val day = if(mes == 2)  28 else  30
        val minDay = Timestamp.valueOf("$ano-$mes-01 00:00:00").getTime()
        val maxDay =  Timestamp.valueOf("$ano-$mes-$day 23:59:59").getTime()
        val diff = maxDay - minDay + 1
        val rnd = Random(ano+mes * ind)
        return Timestamp(minDay + (rnd.nextDouble(1.0) * diff).toLong()).time
    }
}
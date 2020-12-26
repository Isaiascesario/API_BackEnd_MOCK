package com.cunha.isaias.backend.mock.Kotlin.repo

import kotlin.random.Random
import kotlin.random.nextInt

class RandomDescription {
    fun generaterRandomDescrption(seed : Int): String{
        val conso = listOf<String>("b","c","d","f","g","h","j","k","l","m","ç","n","p","q","r","s","t","v","x","z","ss","cr","tr","nh","br","dr","lh")
        val vogal = listOf<String>("a","e","i","o","u")
        var msg = ""
        var rnd = Random(seed)
        val max = rnd.nextInt(20)+10
        for(i in 1..max){
            rnd = Random(seed * i)
            msg += conso[rnd.nextInt(conso.indices)] + vogal[rnd.nextInt(vogal.indices)]
            if(rnd.nextInt(0..conso.size / 2) % 4 == 0)
                msg += " "
        }
        return if(msg.length > 60) msg.substring(0,60) else msg
    }
}
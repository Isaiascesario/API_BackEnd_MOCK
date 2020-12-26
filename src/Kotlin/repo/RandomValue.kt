package com.cunha.isaias.backend.mock.Kotlin.repo

import kotlin.random.Random
import kotlin.random.nextInt

class RandomValue {
    fun generateRandomValue(seed: Int):Int{
        val rnd = Random(seed)
        val max = rnd.nextInt(seed)
        val sign = listOf<Int>(-1,1)
        return max * sign[rnd.nextInt(0..1)]
    }
}
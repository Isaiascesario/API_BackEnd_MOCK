package com.cunha.isaias.backend.mock.Kotlin.repo


const val MAX_ID = 100000
const val MIN_ID = 1000

data class ContractTransaction(
    var descricao: String = "",
    var data: Long = 0L,
    var valor: Int = 0
){
    fun validate(id:Int,mes: Int): Pair<Boolean,String>{
        var msg = ""
        var bool = true
        if(id > MAX_ID || id < MIN_ID){
            msg += "Error ID out of bound,the ID can't be less then $MIN_ID and greater then $MAX_ID \n"
            bool = false
        }
        if(mes > 12 || mes < 1){
            msg += "Error MES out of bound, the MES can't be less then 1 and greater then 12"
            bool = false
        }
        return Pair(bool,msg)
    }

}

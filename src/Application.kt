package com.cunha.isaias.backend.mock


import com.cunha.isaias.backend.mock.Kotlin.repo.ContractTransaction
import com.cunha.isaias.backend.mock.Kotlin.repo.RandomDate
import com.cunha.isaias.backend.mock.Kotlin.repo.RandomDescription
import com.cunha.isaias.backend.mock.Kotlin.repo.RandomValue
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import java.time.Duration
import java.text.DateFormat
import kotlin.IllegalArgumentException

fun Application.main(){
    install(DefaultHeaders)
    install(CORS){
        maxAge = Duration.ofDays(1)
    }
    install(ContentNegotiation){
        gson {  }
    }
    routing {
        get(path = "/{id}/transacoes/{ano}/{mes}") {
                val id = call.parameters["id"] ?: throw IllegalArgumentException("Parameter ID not Found")
                val ano = call.parameters["ano"] ?: throw IllegalArgumentException("Parameter ANO not Found")
                val mes = call.parameters["mes"] ?: throw IllegalArgumentException("Parameter MES not Found")
                val validate = ContractTransaction().validate(id.toInt(), mes.toInt())
                if (!validate.first) {
                    call.respondText(
                            validate.second,
                            ContentType.parse("application/json"),
                            HttpStatusCode.BadRequest
                    )
                    throw IllegalArgumentException(validate.second)
                }
                var idInt = id.toInt()
                while (idInt > 10) {
                    idInt /= 10
                }
                val transactions = mes.toInt() * idInt
                val listOfTransactions = mutableListOf<ContractTransaction>()
                for (i in 1..transactions) {
                    val contractTransaction = ContractTransaction()
                    val seed = (id.toInt() + ano.toInt() + mes.toInt()) * i
                    contractTransaction.descricao = RandomDescription().generaterRandomDescrption(seed)
                    contractTransaction.valor = RandomValue().generateRandomValue(seed)
                    contractTransaction.data = RandomDate().generateRandomDate(ano.toInt(),mes.toInt(),i)
                    listOfTransactions.add(contractTransaction)
                }
                call.respond(listOfTransactions)
        }
    }

}

package com.cunha.isaias.backend.mock

import io.ktor.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

val portArgName = "--server.port"
val defaultPort = 8080

fun main(args: Array<String>){
    val portConfig = args.isNotEmpty() && args[0].startsWith(portArgName)

    val port = if(portConfig) {
        args[0].split("=").last().trim().toInt()
    } else defaultPort

    embeddedServer(Netty, port ,module =  Application::main).start(wait = true)
}
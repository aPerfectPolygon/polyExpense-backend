package com.polygon

import com.polygon.dao.DatabaseFactory
import com.polygon.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
	configureAuthentication(environment.config)
	DatabaseFactory.init(environment.config)
	configureSerialization()
	configureRouting(environment.config)
	configureException()
}

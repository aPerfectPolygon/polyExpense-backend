package com.polygon.plugins

import com.polygon.routing.authRouting
import com.polygon.routing.usersRouting
import com.polygon.services.userService
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(config: ApplicationConfig) {
	routing {
		get("/") {
			call.respondText("Hello World!")
		}
		authRouting(config, userService)
		usersRouting(userService)
	}
}
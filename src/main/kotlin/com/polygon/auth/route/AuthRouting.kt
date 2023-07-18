package com.polygon.auth.route

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.polygon.users.model.User
import com.polygon.users.service.UserService
import com.polygon.utils.jwtAudience
import com.polygon.utils.jwtExpiration
import com.polygon.utils.jwtIssuer
import com.polygon.utils.jwtSecret
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.put
import java.util.*

fun Route.authRouting(config: ApplicationConfig, userService: UserService) {
	post("/login") {
		val user = call.receive<User>()
		val userStatus = userService.loginUser(user.username.orEmpty(), user.password.orEmpty())
		if (userStatus) {
			call.respond(
				buildJsonObject {
					put("description", HttpStatusCode.OK.description)
					put("result", Json.encodeToJsonElement(userService.userRepository.findByUsername(user.username.orEmpty())))
				}
			)
		} else call.respond(HttpStatusCode.Unauthorized, "username/password is wrong")
	}
	
	
	post("/signup") {
		try {
			val user = call.receive<User>()
			val token = JWT.create()
				.withAudience(config.jwtAudience)
				.withIssuer(config.jwtIssuer)
				.withExpiresAt(Date(System.currentTimeMillis() + config.jwtExpiration))
				.sign(Algorithm.HMAC256(config.jwtSecret))
			user.token = token
			userService.createUser(user)
			call.respond(HttpStatusCode.Created, "User singed up")
		} catch (e: Exception) {
			logError(call, e)
		}
	}
}
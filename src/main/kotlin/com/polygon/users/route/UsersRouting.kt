package com.polygon.users.route

import com.polygon.users.model.User
import com.polygon.users.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.put

fun Route.usersRouting(userService: UserService) {
	route("/user") {
		get {
			try {
				val token = call.request.headers["Authorization"] ?: return@get call.respond(
					status = HttpStatusCode.BadRequest, hashMapOf("description" to "Authorization header not found")
				)
				val user = userService.userRepository.findByToken(token) ?: return@get call.respond(
					status = HttpStatusCode.NotFound, hashMapOf("description" to "No User Found")
				)
				return@get if (userService.userRepository.authorized(token)) {
					call.respond(buildJsonObject {
						put("description", HttpStatusCode.OK.description)
						put("result", Json.encodeToJsonElement(user))
					})
				} else call.respond(
					status = HttpStatusCode.Unauthorized, hashMapOf("description" to HttpStatusCode.Unauthorized.description)
				)
			} catch (e: Exception) {
				call.application.environment.log.error(e.toString())
			}
		}
		get("/{id?}") {
			try {
				val token = call.request.headers["Authorization"] ?: return@get call.respond(
					status = HttpStatusCode.BadRequest, hashMapOf("description" to "Authorization header not found")
				)
				val id = call.parameters["id"]?.toLongOrNull() ?: return@get call.respond(
					status = HttpStatusCode.NotFound, hashMapOf("description" to "Missing id")
				)
				val user = userService.userRepository.findById(id) ?: return@get call.respond(
					status = HttpStatusCode.NotFound, hashMapOf("description" to "No User with id: $id")
				)
				return@get if (userService.userRepository.authorized(token)) {
					call.respond(buildJsonObject {
						put("description", HttpStatusCode.OK.description)
						put("result", Json.encodeToJsonElement(user))
					})
				} else call.respond(
					status = HttpStatusCode.Unauthorized, hashMapOf("description" to HttpStatusCode.Unauthorized.description)
				)
			} catch (e: Exception) {
				call.application.environment.log.error(e.toString())
			}
		}
		post {
			val token = call.request.headers["Authorization"] ?: return@post call.respond(
				status = HttpStatusCode.BadRequest, hashMapOf("description" to "Authorization header not found")
			)
			return@post if (userService.userRepository.authorized(token)) {
				val user = call.receive<User>()
				userService.userRepository.updateUser(token, user)
				call.respond(buildJsonObject {
					put("description", HttpStatusCode.OK.description)
					put("result", Json.encodeToJsonElement(userService.userRepository.findByToken(token)))
				})
			} else call.respond(
				status = HttpStatusCode.Unauthorized, hashMapOf("description" to HttpStatusCode.Unauthorized.description)
			)
		}
	}
}
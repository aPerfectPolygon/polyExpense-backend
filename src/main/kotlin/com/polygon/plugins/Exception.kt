package com.polygon.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.sql.SQLException

fun Application.configureException() {
	install(StatusPages) {
		exception<Throwable> { call, cause ->
			call.application.environment.log.also { log ->
				log.info(cause.message)
				call.respond(
					status = when (cause) {
						is AssertionError -> HttpStatusCode.BadRequest
						is SecurityException -> HttpStatusCode.Forbidden
						is SQLException -> HttpStatusCode.BadRequest
						is NotFoundException -> HttpStatusCode.NotFound
						is SerializationException -> HttpStatusCode.BadRequest
						else -> HttpStatusCode.InternalServerError
					},
					message = hashMapOf("description" to cause.message),
				)
			}
		}
		/*status(
			HttpStatusCode.OK,
			HttpStatusCode.Created,
			HttpStatusCode.Accepted,
			HttpStatusCode.BadRequest,
			HttpStatusCode.Unauthorized,
			HttpStatusCode.PaymentRequired,
			HttpStatusCode.Forbidden,
			HttpStatusCode.NotFound,
			HttpStatusCode.MethodNotAllowed,
			HttpStatusCode.NotAcceptable,
			HttpStatusCode.UnsupportedMediaType,
			HttpStatusCode.ExpectationFailed,
			HttpStatusCode.UnprocessableEntity,
			HttpStatusCode.Locked,
			HttpStatusCode.FailedDependency,
			HttpStatusCode.TooEarly,
			HttpStatusCode.UpgradeRequired,
			HttpStatusCode.TooManyRequests,
			HttpStatusCode.InternalServerError,
			HttpStatusCode.NotImplemented,
			HttpStatusCode.BadGateway,
			HttpStatusCode.ServiceUnavailable,
			HttpStatusCode.GatewayTimeout,
		) { call, status ->
			call.respond(
				buildJsonObject {
					put("description", status.description)
				}
			)
		}*/
	}
}
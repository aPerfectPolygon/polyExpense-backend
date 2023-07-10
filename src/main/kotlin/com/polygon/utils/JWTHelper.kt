package com.polygon.utils

import io.ktor.server.config.*


val ApplicationConfig.jwtSecret
	get() = property("jwt.secret").getString()
val ApplicationConfig.jwtIssuer
	get() = property("jwt.issuer").getString()
val ApplicationConfig.jwtAudience
	get() = property("jwt.audience").getString()
val ApplicationConfig.jwtExpiration
	get() = property("jwt.expiration").getString().toInt()
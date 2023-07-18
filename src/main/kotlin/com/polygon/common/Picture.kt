package com.polygon.common

import kotlinx.serialization.Serializable

@Serializable
data class Picture(
	val small: String? = "", // string
	val medium: String? = "", // string
	val large: String? = "" // string
)
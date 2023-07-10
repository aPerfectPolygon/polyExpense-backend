package com.polygon.model

import com.google.gson.annotations.Expose
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User @JvmOverloads constructor(
	@Expose val id: Long? = 0,
	@Expose val firstName: String? = "",
	@Expose val lastName: String? = "",
	@Expose val username: String,
	@Expose val password: String,
	@Expose val email: String? = "",
	@Expose val registrationStatus: String? = "",
	@Expose val picture: Picture? = Picture(
		small = "",
		medium = "",
		large = ""
	),
	@Expose val notificationsRead: String? = "",
	@Expose val notificationsCount: Int? = 0,
	@Expose val notifications: Notifications? = Notifications(
		addedAsFriend = false
	),
	@Expose val defaultCurrency: String? = "",
	@Expose val locale: String? = "",
	@Expose var token: String? = ""
)

@Serializable
data class Notifications(
	@Expose val addedAsFriend: Boolean?
)

@Serializable
data class Picture(
	@Expose val small: String?, @Expose val medium: String?, @Expose val large: String?
)

object Users : Table("users") {
	val id = long("id").autoIncrement()
	val firstName = varchar("firstName", 128)
	val lastName = varchar("lastName", 128)
	val username = varchar("username", 128).uniqueIndex()
	val password = varchar("password", 128)
	val email = varchar("email", 128)
	val registrationStatus = varchar("registrationStatus", 128)
	val smallPicture = varchar("smallPicture", 128)
	val mediumPicture = varchar("mediumPicture", 128)
	val largePicture = varchar("largePicture", 128)
	val notificationsRead = varchar("notificationsRead", 128)
	val notificationsCount = integer("notificationsCount")
	val addedAsFriend = bool("addedAsFriend")
	val defaultCurrency = varchar("defaultCurrency", 128)
	val locale = varchar("locale", 128)
	val token = varchar("token", 256)
}
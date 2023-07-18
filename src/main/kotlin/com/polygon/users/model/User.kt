package com.polygon.users.model

import com.polygon.common.Picture
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class User @JvmOverloads constructor(
	val id: Long? = 0,
	val firstName: String? = "",
	val lastName: String? = "",
	val username: String? = "",
	val password: String? = "",
	val email: String? = "",
	val registrationStatus: String? = "",
	val picture: Picture? = Picture(),
	val notificationsRead: String? = "",
	val notificationsCount: Int? = 0,
	val notifications: Notifications? = Notifications(),
	val defaultCurrency: String? = "",
	val locale: String? = "",
	val balance: List<Balance?>? = listOf(),
	val paidShare: String? = "", // 8.99
	val owedShare: String? = "", // 4.5
	val netBalance: String? = "", // 4.49
	var token: String? = ""
) {
	@Serializable
	data class Balance(
		val currencyCode: String? = "", // USD
		val amount: String? = "" // -5.02
	)
	
	@Serializable
	data class Notifications(
		val addedAsFriend: Boolean? = false
	)
}

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
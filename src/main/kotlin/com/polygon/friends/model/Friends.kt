package com.polygon.friends.model

import com.polygon.common.Picture
import com.polygon.users.model.User
import kotlinx.serialization.Serializable


@Serializable
data class Friends(
	val friends: List<Friend?>? = listOf()
) {
	@Serializable
	data class Friend(
		val id: Int? = 0, // 0
		val firstName: String? = "", // Ada
		val lastName: String? = "", // Lovelace
		val email: String? = "", // ada@example.com
		val registrationStatus: String? = "", // confirmed
		val picture: Picture? = Picture(),
		val groups: List<Group?>? = listOf(),
		val balance: List<User.Balance?>? = listOf(),
		val updatedAt: String? = "" // 2019-08-24T14:15:22Z
	) {
		@Serializable
		data class Group(
			val groupId: Int? = 0, // 571
			val balance: List<User.Balance?>? = listOf()
		)
	}
}
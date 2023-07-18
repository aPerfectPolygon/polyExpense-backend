package com.polygon.comments.model

import com.polygon.users.model.User
import kotlinx.serialization.Serializable


@Serializable
data class Comments(
	val comments: List<Comment?>? = listOf()
) {
	@Serializable
	data class Comment(
		val id: Int? = 0, // 79800950
		val content: String? = "", // John D. updated this transaction: - The cost changed from $6.99 to $8.99
		val commentType: String? = "", // System
		val relationType: String? = "", // ExpenseComment
		val relationId: Int? = 0, // 855870953
		val createdAt: String? = "", // 2019-08-24T14:15:22Z
		val deletedAt: String? = "", // 2019-08-24T14:15:22Z
		val user: User? = User()
	)
}
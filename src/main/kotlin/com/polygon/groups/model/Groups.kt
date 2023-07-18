package com.polygon.groups.model

import com.polygon.common.Picture
import com.polygon.users.model.User
import kotlinx.serialization.Serializable


@Serializable
data class Groups(
	val groups: List<Group?>? = listOf()
) {
	@Serializable
	data class Group(
		val id: Int? = 0, // 321
		val name: String? = "", // Housemates 2020
		val groupType: String? = "", // apartment
		val updatedAt: String? = "", // 2019-08-24T14:15:22Z
		val simplifyByDefault: Boolean? = false, // true
		val members: List<User?>? = listOf(),
		val originalDebts: List<OriginalDebt?>? = listOf(),
		val simplifiedDebts: List<SimplifiedDebt?>? = listOf(),
		val avatar: Avatar? = Avatar(),
		val customAvatar: Boolean? = false, // true
		val coverPhoto: CoverPhoto? = CoverPhoto(),
		val inviteLink: String? = "" // https://www.splitwise.com/join/abQwErTyuI+12
	) {
		@Serializable
		data class OriginalDebt(
			val from: Int? = 0, // 18523
			val to: Int? = 0, // 90261
			val amount: String? = "", // 414.5
			val currencyCode: String? = "" // USD
		)
		
		@Serializable
		data class SimplifiedDebt(
			val from: Int? = 0, // 18523
			val to: Int? = 0, // 90261
			val amount: String? = "", // 414.5
			val currencyCode: String? = "" // USD
		)
		
		@Serializable
		data class Avatar(
			val original: String? = "", // null
			val xxlarge: String? = "", // https://s3.amazonaws.com/splitwise/uploads/group/default_avatars/avatar-ruby2-house-1000px.png
			val xlarge: String? = "", // https://s3.amazonaws.com/splitwise/uploads/group/default_avatars/avatar-ruby2-house-500px.png
			val large: String? = "", // https://s3.amazonaws.com/splitwise/uploads/group/default_avatars/avatar-ruby2-house-200px.png
			val medium: String? = "", // https://s3.amazonaws.com/splitwise/uploads/group/default_avatars/avatar-ruby2-house-100px.png
			val small: String? = "" // https://s3.amazonaws.com/splitwise/uploads/group/default_avatars/avatar-ruby2-house-50px.png
		)
		
		@Serializable
		data class CoverPhoto(
			val xxlarge: String? = "", // https://s3.amazonaws.com/splitwise/uploads/group/default_cover_photos/coverphoto-ruby-1000px.png
			val xlarge: String? = "" // https://s3.amazonaws.com/splitwise/uploads/group/default_cover_photos/coverphoto-ruby-500px.png
		)
	}
}
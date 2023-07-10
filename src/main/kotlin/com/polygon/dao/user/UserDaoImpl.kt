package com.polygon.dao.user

import com.polygon.dao.DatabaseFactory.dbQuery
import com.polygon.model.Notifications
import com.polygon.model.Picture
import com.polygon.model.User
import com.polygon.model.Users
import org.jetbrains.exposed.sql.*

class UserDaoImpl : UserDaoFacade {
	
	private fun resultRowToUser(row: ResultRow) = User(
		id = row[Users.id],
		firstName = row[Users.firstName],
		lastName = row[Users.lastName],
		username = row[Users.username],
		password = row[Users.password],
		email = row[Users.email],
		registrationStatus = row[Users.registrationStatus],
		picture = Picture(
			small = row[Users.smallPicture],
			medium = row[Users.mediumPicture],
			large = row[Users.largePicture],
		),
		notificationsRead = row[Users.notificationsRead],
		notificationsCount = row[Users.notificationsCount],
		notifications = Notifications(addedAsFriend = row[Users.addedAsFriend]),
		defaultCurrency = row[Users.defaultCurrency],
		locale = row[Users.locale],
		token = row[Users.token]
	)
	
	override suspend fun createUser(user: User): User? = dbQuery {
		val insertStatement = Users.insert {
			it[firstName] = user.firstName.orEmpty()
			it[lastName] = user.lastName.orEmpty()
			it[username] = user.username
			it[password] = user.password
			it[email] = user.email.orEmpty()
			it[registrationStatus] = user.registrationStatus.orEmpty()
			it[smallPicture] = user.picture?.small.orEmpty()
			it[mediumPicture] = user.picture?.medium.orEmpty()
			it[largePicture] = user.picture?.large.orEmpty()
			it[notificationsRead] = user.notificationsRead.orEmpty()
			it[notificationsCount] = user.notificationsCount ?: 0
			it[addedAsFriend] = user.notifications?.addedAsFriend ?: false
			it[defaultCurrency] = user.defaultCurrency.orEmpty()
			it[locale] = user.locale.orEmpty()
			it[token] = user.token.orEmpty()
		}
		insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
	}
	
	override suspend fun updateUser(token: String, user: User): Boolean = dbQuery {
		Users.update({ Users.token eq token }) {
			if (!user.firstName.isNullOrEmpty()) it[firstName] = user.firstName
			if (!user.lastName.isNullOrEmpty()) it[lastName] = user.lastName
			if (user.username.isNotEmpty()) it[username] = user.username
			if (user.password.isNotEmpty()) it[password] = user.password
			if (!user.email.isNullOrEmpty()) it[email] = user.email
			if (!user.registrationStatus.isNullOrEmpty()) it[registrationStatus] = user.registrationStatus
			if (!user.picture?.small.isNullOrEmpty()) it[smallPicture] = user.picture?.small.orEmpty()
			if (!user.picture?.medium.isNullOrEmpty()) it[mediumPicture] = user.picture?.medium.orEmpty()
			if (!user.picture?.large.isNullOrEmpty()) it[largePicture] = user.picture?.large.orEmpty()
			if (!user.notificationsRead.isNullOrEmpty()) it[notificationsRead] = user.notificationsRead
			if (user.notificationsCount != 0) it[notificationsCount] = user.notificationsCount ?: 0
			if (!user.defaultCurrency.isNullOrEmpty()) it[defaultCurrency] = user.defaultCurrency
			if (!user.locale.isNullOrEmpty()) it[locale] = user.locale
		} > 0
	}
	
	override suspend fun findById(id: Long): User? = dbQuery {
		Users.select { Users.id eq id }.map(::resultRowToUser).singleOrNull()
	}
	
	override suspend fun findByUsername(username: String): User? = dbQuery {
		Users.select { Users.username eq username }.map(::resultRowToUser).singleOrNull()
	}
	
	override suspend fun findByToken(token: String): User? = dbQuery {
		Users.select { Users.token eq token }.map(::resultRowToUser).singleOrNull()
	}
	
	override suspend fun authorized(token: String): Boolean = dbQuery {
		Users.select { Users.token eq token }.map(::resultRowToUser).singleOrNull() != null
	}
	
	override suspend fun users(): List<User?> = dbQuery {
		Users.selectAll().distinct().map(::resultRowToUser)
	}
}

val userDAO = UserDaoImpl()
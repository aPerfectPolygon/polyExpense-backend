package com.polygon.users.service

import com.polygon.users.dao.UserDaoFacade
import com.polygon.users.dao.userDAO
import com.polygon.users.model.User
import com.polygon.utils.Hasher
import io.ktor.server.plugins.*

class UserService(val userRepository: UserDaoFacade) {
	suspend fun createUser(user: User): User? = userRepository.createUser(
		User(
			id = user.id,
			firstName = user.firstName,
			lastName = user.lastName,
			username = user.username,
			password = Hasher.hashPassword(user.password),
			email = user.email,
			registrationStatus = user.registrationStatus,
			picture = user.picture,
			notificationsRead = user.notificationsRead,
			notificationsCount = user.notificationsCount,
			notifications = user.notifications,
			defaultCurrency = user.defaultCurrency,
			locale = user.locale,
			token = user.token
		)
	)
	
	suspend fun loginUser(username: String, password: String): Boolean {
		val user = userRepository.findByUsername(username)
		when {
			user != null -> when {
				Hasher.checkPassword(password, user) -> return true
				else -> throw AssertionError("Wrong Password")
			}
			
			else -> throw NotFoundException("user not found")
		}
	}
	
	
}

val userService = UserService(userDAO)
package com.polygon.dao.user

import com.polygon.model.User

interface UserDaoFacade {
	suspend fun createUser(user: User): User?
	suspend fun updateUser(token: String, user: User): Boolean
	suspend fun findById(id: Long): User?
	suspend fun findByUsername(username: String): User?
	suspend fun findByToken(token: String): User?
	suspend fun authorized(token: String): Boolean
	suspend fun users(): List<User?>?
}
package com.polygon.expenses.model

import com.polygon.users.model.User
import kotlinx.serialization.Serializable


@Serializable
data class Expenses(
	val expense: Expense? = Expense()
) {
	@Serializable
	data class Expense(
		val cost: String? = "", // 25.0
		val description: String? = "", // Brunch
		val details: String? = "", // string
		val date: String? = "", // 2012-05-02T13:00:00Z
		val repeatInterval: String? = "", // never
		val currencyCode: String? = "", // USD
		val categoryId: Int? = 0, // 15
		val id: Int? = 0, // 51023
		val groupId: Int? = 0, // 391
		val friendshipId: Int? = 0, // 4818
		val expenseBundleId: Int? = 0, // 491030
		val repeats: Boolean? = false, // true
		val emailReminder: Boolean? = false, // true
		val emailReminderInAdvance: Boolean? = false, // null
		val nextRepeat: String? = "", // string
		val commentsCount: Int? = 0, // 0
		val payment: Boolean? = false, // true
		val transactionConfirmed: Boolean? = false, // true
		val repayments: List<Repayment?>? = listOf(),
		val createdAt: String? = "", // 2012-07-27T06:17:09Z
		val createdBy: User? = User(),
		val updatedAt: String? = "", // 2012-12-23T05:47:02Z
		val updatedBy: User? = User(),
		val deletedAt: String? = "", // 2012-12-23T05:47:02Z
		val deletedBy: User? = User(),
		val category: Category? = Category(),
		val receipt: Receipt? = Receipt(),
		val users: List<User?>? = listOf(),
		val comments: List<Comment?>? = listOf()
	) {
		@Serializable
		data class Repayment(
			val from: Int? = 0, // 6788709
			val to: Int? = 0, // 270896089
			val amount: String? = "" // 25.0
		)
		
		@Serializable
		data class Category(
			val id: Int? = 0, // 5
			val name: String? = "" // Electricity
		)
		
		@Serializable
		data class Receipt(
			val large: String? = "", // https://splitwise.s3.amazonaws.com/uploads/expense/receipt/3678899/large_95f8ecd1-536b-44ce-ad9b-0a9498bb7cf0.png
			val original: String? = "" // https://splitwise.s3.amazonaws.com/uploads/expense/receipt/3678899/95f8ecd1-536b-44ce-ad9b-0a9498bb7cf0.png
		)
		
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
}
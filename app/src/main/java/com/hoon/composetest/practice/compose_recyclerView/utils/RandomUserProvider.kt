package com.hoon.composetest.practice.compose_recyclerView.utils

data class RandomUser(
    val name: String = "μ μν π",
    val description: String = "μλνμΈμ hoon μλλ€.π",
    val profileImage: String = "https://randomuser.me/api/portraits/women/23.jpg"
)

object RandomUserProvider {
    val userList = List(200) { RandomUser() }

}
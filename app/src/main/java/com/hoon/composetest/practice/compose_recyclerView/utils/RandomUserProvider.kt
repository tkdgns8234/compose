package com.hoon.composetest.practice.compose_recyclerView.utils

data class RandomUser(
    val name: String = "정상훈 😎",
    val description: String = "안녕하세요 hoon 입니다.😀",
    val profileImage: String = "https://randomuser.me/api/portraits/women/23.jpg"
)

object RandomUserProvider {
    val userList = List(200) { RandomUser() }

}
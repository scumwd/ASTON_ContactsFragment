package com.example.aston_contactsfragment.models

import java.io.Serializable

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val photoUrl: String

) : Serializable

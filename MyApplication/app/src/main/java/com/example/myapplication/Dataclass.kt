package com.example.myapplication

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Dataclass (
    val id:Int,
    val name: String,
    val descriptionText: String
)
@Serializable
data class Datas(
    val Datas: List<Dataclass>
)
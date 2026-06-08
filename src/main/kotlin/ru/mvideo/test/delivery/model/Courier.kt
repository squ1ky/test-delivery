package ru.mvideo.test.delivery.model

data class Courier(
    val id: Long,
    val name: String,
    var isAvailable: Boolean,
    val maxWeight: Double,
    val rating: Double
)

package ru.mvideo.test.delivery.model

data class Delivery(
    val id: Long = 0L,
    val address: String,
    val orderWeight: Double,
    val zoneId: String,
    var status: DeliveryStatus = DeliveryStatus.CREATED,
    var courier: Courier? = null
)


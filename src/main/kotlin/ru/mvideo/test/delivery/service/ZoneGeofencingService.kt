package ru.mvideo.test.delivery.service

import org.springframework.stereotype.Service

@Service
class ZoneGeofencingService {
    private val zones = listOf(
        Zone(
            id = "ZONE_1",
            enable = true
        ),
        Zone(
            id = "ZONE_2",
            enable = false
        ),
    )

    fun verifyAccess(zoneId: String): Boolean {
        return zones.first { it.id == zoneId }?.enable == true
    }
}

data class Zone(
    val id: String,
    val enable: Boolean,
)

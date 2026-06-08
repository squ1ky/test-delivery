package ru.mvideo.test.delivery.service

import org.springframework.stereotype.Service

@Service
class RouteOptimizationService(
    private val zoneGeofencingService: ZoneGeofencingService
) {
    fun checkZoneAccess(courierId: Long, zoneId: String): Boolean {
        return zoneGeofencingService.verifyAccess(zoneId)
    }
}

package ru.mvideo.test.delivery.service

import org.springframework.stereotype.Service
import ru.mvideo.test.delivery.model.Courier
import ru.mvideo.test.delivery.repository.CourierRepository

@Service
class CourierAssignmentService(
    private val routeOptimizationService: RouteOptimizationService,
    private val courierRepository: CourierRepository
) {

    fun findBestCourier(orderWeight: Double, zoneId: String): Courier? =
        courierRepository.findAll()
            .filter { it.isAvailable }
            .filter { it.maxWeight >= orderWeight }
            .filter { it.rating >= MIN_RATING }
            .filter { routeOptimizationService.checkZoneAccess(it.id, zoneId) }
            .maxByOrNull { it.rating }

    private companion object {
        const val MIN_RATING = 4.0
    }
}

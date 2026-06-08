package ru.mvideo.test.delivery.service

import org.springframework.stereotype.Service
import ru.mvideo.test.delivery.model.Courier
import ru.mvideo.test.delivery.repository.CourierRepository

@Service
class CourierAssignmentService(
    private val routeOptimizationService: RouteOptimizationService,
    private val courierRepository: CourierRepository
) {

    fun findBestCourier(orderWeight: Double, zoneId: String): Courier? {
        val couriers = courierRepository.findAll().toList()
        var bestCourier: Courier? = null
        for (i in 0 until couriers.size) {
            val courier = couriers[i]
            if (courier.isAvailable) {
                if (courier.maxWeight >= orderWeight) {
                    if (courier.rating >= 4.0) {
                        if (routeOptimizationService.checkZoneAccess(courier.id, zoneId)) {
                            if (bestCourier == null) {
                                bestCourier = courier
                            } else {
                                if (courier.rating > bestCourier.rating) {
                                    bestCourier = courier
                                }
                            }
                        }
                    }
                }
            }
        }
        return bestCourier
    }
}

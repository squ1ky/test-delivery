package ru.mvideo.test.delivery.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mvideo.test.delivery.model.Delivery
import ru.mvideo.test.delivery.model.DeliveryStatus
import ru.mvideo.test.delivery.service.DeliveryService

data class StatusUpdateRequest(val status: DeliveryStatus)

@RestController
@RequestMapping("/api/delivery")
class DeliveryController(
    private val deliveryService: DeliveryService,
) {

    @GetMapping("/assign")
    fun assignCourier(
        @RequestParam deliveryId: Long,
    ): Delivery? {
        return deliveryService.assignCourierToDelivery(deliveryId)
    }

    @PutMapping("/{id}/status")
    fun changeStatus(
        @PathVariable id: Long,
        @RequestBody request: StatusUpdateRequest,
    ): Delivery {
        return deliveryService.updateStatus(id, request.status)
    }
}

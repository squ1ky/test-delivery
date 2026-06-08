package ru.mvideo.test.delivery.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mvideo.test.delivery.model.Delivery
import ru.mvideo.test.delivery.service.DeliveryService

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

    // Функция для смены статуса заказов
    fun changeStatus(){
        TODO()
    }
}

package ru.mvideo.test.delivery.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import ru.mvideo.test.delivery.model.Courier
import ru.mvideo.test.delivery.model.Delivery
import ru.mvideo.test.delivery.model.DeliveryStatus
import ru.mvideo.test.delivery.repository.DeliveryRepository

class DeliveryServiceTest {

    private val deliveryRepository: DeliveryRepository = mockk()
    private val courierAssignmentService: CourierAssignmentService = mockk()

    private val deliveryService = DeliveryService(deliveryRepository, courierAssignmentService)

    @Test
    fun `assignCourierToDelivery should assign courier and update status when best courier is found`() {
        val deliveryId = 1L
        val delivery = Delivery(id = deliveryId, address = "Test Address", orderWeight = 10.0, zoneId = "ZONE_1", status = DeliveryStatus.CREATED)
        val courier = Courier(id = 1L, name = "John", isAvailable = true, maxWeight = 20.0, rating = 4.5)

        every { deliveryRepository.findById(deliveryId) } returns delivery
        every { courierAssignmentService.findBestCourier(10.0, "ZONE_1") } returns courier
        every { deliveryRepository.save(any()) } answers { firstArg() }

        val result = deliveryService.assignCourierToDelivery(deliveryId)

        assertNotNull(result)
        assertEquals(DeliveryStatus.ASSIGNED, result?.status)
        assertEquals(courier, result?.courier)

        verify { deliveryRepository.findById(deliveryId) }
        verify { courierAssignmentService.findBestCourier(10.0, "ZONE_1") }
        verify { deliveryRepository.save(delivery) }
    }

    @Test
    fun `assignCourierToDelivery should not change status when best courier is not found`() {
        val deliveryId = 1L
        val delivery = Delivery(id = deliveryId, address = "Test Address", orderWeight = 10.0, zoneId = "ZONE_1", status = DeliveryStatus.CREATED)

        every { deliveryRepository.findById(deliveryId) } returns delivery
        every { courierAssignmentService.findBestCourier(10.0, "ZONE_1") } returns null

        val result = deliveryService.assignCourierToDelivery(deliveryId)

        assertNotNull(result)
        assertEquals(DeliveryStatus.CREATED, result?.status)
        assertNull(result?.courier)

        verify { deliveryRepository.findById(deliveryId) }
        verify { courierAssignmentService.findBestCourier(10.0, "ZONE_1") }
        verify(exactly = 0) { deliveryRepository.save(any()) }
    }

    @Test
    fun `assignCourierToDelivery should return null when delivery not found`() {
        val deliveryId = 1L

        every { deliveryRepository.findById(deliveryId) } returns null

        val result = deliveryService.assignCourierToDelivery(deliveryId)

        assertNull(result)

        verify { deliveryRepository.findById(deliveryId) }
        verify(exactly = 0) { courierAssignmentService.findBestCourier(any(), any()) }
        verify(exactly = 0) { deliveryRepository.save(any()) }
    }
}


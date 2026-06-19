package ru.mvideo.test.delivery.service

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import ru.mvideo.test.delivery.exception.InvalidDeliveryStatusTransitionException
import ru.mvideo.test.delivery.model.DeliveryStatus

class DeliveryStatusTransitionValidatorTest {

    @Test
    fun `allows transition from CREATED to IN_TRANSIT`() {
        assertTrue(DeliveryStatusTransitionValidator.isValidTransition(DeliveryStatus.CREATED, DeliveryStatus.IN_TRANSIT))
    }

    @Test
    fun `allows transition from IN_TRANSIT to DELIVERED`() {
        assertTrue(DeliveryStatusTransitionValidator.isValidTransition(DeliveryStatus.IN_TRANSIT, DeliveryStatus.DELIVERED))
    }

    @Test
    fun `forbids skipping IN_TRANSIT when moving from CREATED to DELIVERED`() {
        assertFalse(DeliveryStatusTransitionValidator.isValidTransition(DeliveryStatus.CREATED, DeliveryStatus.DELIVERED))
    }

    @Test
    fun `validateTransition throws for invalid transition`() {
        assertThrows(InvalidDeliveryStatusTransitionException::class.java) {
            DeliveryStatusTransitionValidator.validateTransition(DeliveryStatus.CREATED, DeliveryStatus.DELIVERED)
        }
    }
}

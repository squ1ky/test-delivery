package ru.mvideo.test.delivery.service

import ru.mvideo.test.delivery.exception.InvalidDeliveryStatusTransitionException
import ru.mvideo.test.delivery.model.DeliveryStatus
import ru.mvideo.test.delivery.model.DeliveryStatus.ASSIGNED
import ru.mvideo.test.delivery.model.DeliveryStatus.CREATED
import ru.mvideo.test.delivery.model.DeliveryStatus.DELIVERED
import ru.mvideo.test.delivery.model.DeliveryStatus.IN_TRANSIT

object DeliveryStatusTransitionValidator {

    private val allowedTransitions: Map<DeliveryStatus, Set<DeliveryStatus>> = mapOf(
        CREATED to setOf(IN_TRANSIT),
        ASSIGNED to setOf(IN_TRANSIT),
        IN_TRANSIT to setOf(DELIVERED)
    )

    fun isValidTransition(from: DeliveryStatus, to: DeliveryStatus): Boolean =
        to in allowedTransitions[from].orEmpty()

    fun validateTransition(from: DeliveryStatus, to: DeliveryStatus) {
        if (!isValidTransition(from, to)) {
            throw InvalidDeliveryStatusTransitionException(
                "Cannot change delivery status from $from to $to"
            )
        }
    }
}

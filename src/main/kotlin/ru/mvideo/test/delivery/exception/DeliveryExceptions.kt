package ru.mvideo.test.delivery.exception

class DeliveryNotFoundException(deliveryId: Long) : RuntimeException("Delivery with id=$deliveryId not found")

class InvalidDeliveryStatusTransitionException(message: String) : RuntimeException(message)

package ru.mvideo.test.delivery.repository

import org.springframework.stereotype.Repository
import ru.mvideo.test.delivery.model.Delivery
import ru.mvideo.test.delivery.model.DeliveryStatus

interface DeliveryRepository {
    fun save(delivery: Delivery): Delivery
    fun findById(id: Long): Delivery?
    fun findAll(): List<Delivery>
}

@Repository
class FakeDeliveryRepositoryImpl : DeliveryRepository {
    private val storage = mutableMapOf<Long, Delivery>()
    private var sequence = 4L

    init {
        storage[1L] = Delivery(1L, "ул. Ленина, 10", 5.0, "ZONE_1", DeliveryStatus.CREATED, null)
        storage[2L] = Delivery(2L, "ул. Пушкина, 5", 15.0, "ZONE_2", DeliveryStatus.CREATED, null)
        storage[3L] = Delivery(3L, "пр. Мира, 120", 2.5, "ZONE_1", DeliveryStatus.CREATED, null)
    }

    override fun save(delivery: Delivery): Delivery {
        val idToUse = if (delivery.id == 0L) sequence++ else delivery.id
        val savedEntity = delivery.copy(id = idToUse)
        storage[idToUse] = savedEntity
        return savedEntity
    }

    override fun findById(id: Long): Delivery? = storage[id]

    override fun findAll(): List<Delivery> = storage.values.toList()
}


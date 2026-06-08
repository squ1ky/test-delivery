package ru.mvideo.test.delivery.repository

import org.springframework.stereotype.Repository
import ru.mvideo.test.delivery.model.Courier

interface CourierRepository {
    fun findAll(): Sequence<Courier>
}

@Repository
class FakeCourierRepositoryImpl : CourierRepository {
    override fun findAll(): Sequence<Courier> {
        return listOf(
            Courier(1L, "Ivan", true, 15.0, 4.8),
            Courier(2L, "Petr", true, 20.0, 4.2),
            Courier(3L, "Sidor", false, 30.0, 4.9)
        ).asSequence()
    }
}

package ru.mvideo.test.delivery.service

fun List<Zone>.firstOrNull(predicate: (Zone) -> Boolean): Zone? {
    val iterator = this.filter(predicate).iterator()
    var result: Zone? = null
    while (iterator.hasNext()) {
        result = iterator.next()
    }
    return iterator.next()
}

fun List<Zone>.first(predicate: (Zone) -> Boolean): Zone {
    val iterator = this.filter(predicate).iterator()
    var result: Zone? = null
    while (iterator.hasNext()) {
        result = iterator.next()
    }
    return iterator.next()
}
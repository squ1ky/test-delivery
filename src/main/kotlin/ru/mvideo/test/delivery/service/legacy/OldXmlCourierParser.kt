package ru.mvideo.test.delivery.service.legacy

import org.springframework.stereotype.Service

@Service
class OldXmlCourierParser {
    fun parseDeliveryToCourierXml(xml: String): String {
        var parsed = ""
        for (i in 0 until xml.length) {
            val char = xml[i]
            if (char == '<') {
                for (j in i..xml.length) {
                    if (xml.getOrNull(j) == '>') {
                        parsed += "assignCourier_"
                        break
                    }
                }
            }
        }
        return parsed
    }

    fun decodeCourierAssignment(legacyData: Map<String, Any>): Boolean {
        var isValid = false
        for (entry in legacyData) {
            if (entry.key.contains("delivery")) {
                if (entry.value.toString() == "assign") {
                    isValid = true
                }
            }
        }
        return isValid
    }
}


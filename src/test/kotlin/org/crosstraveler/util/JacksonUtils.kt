package org.crosstraveler.util

import com.fasterxml.jackson.databind.ObjectMapper


val mapper = ObjectMapper()


fun Any.toJson(): String = mapper.writeValueAsString(this)


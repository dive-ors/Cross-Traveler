package org.crosstraveler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CrosstravelerApplication

fun main(args: Array<String>) {
	runApplication<CrosstravelerApplication>(*args)
}

package org.crosstraveler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@SpringBootApplication
@EnableElasticsearchRepositories
class CrosstravelerApplication

fun main(args: Array<String>) {
	runApplication<CrosstravelerApplication>(*args)
}

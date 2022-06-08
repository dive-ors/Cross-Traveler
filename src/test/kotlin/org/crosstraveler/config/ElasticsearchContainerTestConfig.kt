package org.crosstraveler.config

import org.crosstraveler.config.ElasticSearchContainer.Companion.CONTAINER
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients

@TestConfiguration
@Profile("test")
class ElasticsearchContainerTestConfig {

    @Bean
    fun elasticsearchClient(): RestHighLevelClient {
        val config = ClientConfiguration.builder()
            .connectedTo(CONTAINER.httpHostAddress)
            .build()

        return RestClients.create(config).rest()
    }

}
package org.crosstraveler.config

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration

import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.http.HttpHeaders


import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class ElasticsearchConfig {


    @Bean
    fun elasticsearchClient(): RestHighLevelClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .withConnectTimeout(Duration.ofSeconds(2))
            .withSocketTimeout(Duration.ofSeconds(3))
            .withHeaders {
                val headers = HttpHeaders()
                headers.add("currentTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                headers
            }
            .build()
        return RestClients.create(clientConfiguration).rest()

    }

    @Bean
    fun elasticsearchTemplate(): ElasticsearchOperations {
        return ElasticsearchRestTemplate(elasticsearchClient())
    }
}
package org.crosstraveler.config

import io.mockk.mockk
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@TestConfiguration
class ElasticSearchMockConfig {

    @Bean
    fun elasticsearchClient(): RestHighLevelClient = mockk<RestHighLevelClient>()

}
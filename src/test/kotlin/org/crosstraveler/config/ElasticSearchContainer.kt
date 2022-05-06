package org.crosstraveler.config

import org.testcontainers.elasticsearch.ElasticsearchContainer
import org.testcontainers.junit.jupiter.Container

class ElasticSearchContainer {
    companion object {
        @Container
        @JvmStatic
        val CONTAINER = ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.15.2")
            .apply { start() }
    }
}
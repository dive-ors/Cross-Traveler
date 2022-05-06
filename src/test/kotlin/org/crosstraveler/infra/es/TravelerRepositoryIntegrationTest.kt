package org.crosstraveler.infra.es

import org.crosstraveler.application.SkillTraveler
import org.crosstraveler.application.SkillTravelerRepository
import org.crosstraveler.config.ElasticsearchTestConfig
import org.crosstraveler.config.TestContainerIntegrationTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.test.context.TestConstructor


@TestContainerIntegrationTest
internal class TravelerRepositoryIntegrationTest {
    private val log = LoggerFactory.getLogger(javaClass)


    @Autowired
    private lateinit var travelerRepository: SkillTravelerRepository
    @BeforeEach
    fun setUp() {
        travelerRepository.deleteAll()
    }


    @Test
    fun getTraveler() {

        val fixture = SkillTraveler("탕수륙", "숲속고양이", listOf("파이어익스퍼트1"))
        travelerRepository.save(fixture)


        val response = travelerRepository.findBySkillNameLike("파이어익스퍼트1")

        log.info("${response[0].userName} : ${response[0].skillName}")

        assertEquals(1, response.size)

    }

}

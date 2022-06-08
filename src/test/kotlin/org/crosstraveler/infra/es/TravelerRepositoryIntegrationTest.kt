package org.crosstraveler.infra.es

import org.crosstraveler.application.traveler.RequiredSkill
import org.crosstraveler.application.traveler.RequiredSkillRepository
import org.crosstraveler.config.TestContainerIntegrationTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired


@TestContainerIntegrationTest
internal class TravelerRepositoryIntegrationTest {
    private val log = LoggerFactory.getLogger(javaClass)


    @Autowired
    private lateinit var travelerRepository: RequiredSkillRepository

    @BeforeEach
    fun setUp() {
        travelerRepository.deleteAll()
    }


    @Test
    fun getTraveler() {

        val fixture = RequiredSkill("탕수륙", "숲속고양이", listOf("우드익스퍼트1","우드익스퍼트2","푸드파이터3"))
        travelerRepository.save(fixture)


        val response = travelerRepository.findBySkillNameIn(listOf("파이어익스퍼트2","푸드파이터3"))

        log.info("${response[0].userName} : ${response[0].skillName}")

        assertEquals(1, response.size)

    }

}

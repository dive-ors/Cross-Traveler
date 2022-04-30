package org.crosstraveler.infra.es

import org.crosstraveler.application.SkillTravelerRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class TravelerRepositoryIntegrationTest(val repository: SkillTravelerRepository) {
    private val log = LoggerFactory.getLogger(javaClass)


    @Test
    fun getTraveler() {
        /*
        val fixture = SkillDto("탕수륙", "숲속고양이", listOf("파이어익스퍼트1"))
        repository.save(fixture)
        */

        val response = repository.findBySkillNameLike("파이어익스퍼트1")

        log.info("${response[0].userName} : ${response[0].skillName}")

        assertEquals(1, response.size)

    }
}

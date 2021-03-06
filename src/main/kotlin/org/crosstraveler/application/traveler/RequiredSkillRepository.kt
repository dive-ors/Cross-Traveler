package org.crosstraveler.application.traveler

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface RequiredSkillRepository : ElasticsearchRepository<RequiredSkill, String> {
    fun findByUserName(userName: String): RequiredSkill?

    // https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.repositories#elasticsearch.query-methods.criterions
    fun findBySkillNameIn(skillName: List<String>): List<RequiredSkill>
}
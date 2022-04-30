package org.crosstraveler.application

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface SkillTravelerRepository : ElasticsearchRepository<SkillTraveler, String> {
    fun findByUserName(userName: String, pageable: Pageable): Page<SkillTraveler>

    // https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.repositories#elasticsearch.query-methods.criterions
    fun findBySkillNameLike(skillName: String): List<SkillTraveler>
}
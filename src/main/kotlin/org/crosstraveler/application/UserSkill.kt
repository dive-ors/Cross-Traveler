package org.crosstraveler.application

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.WriteTypeHint

// 기능이 없어서, domain 이라 부르기 애매함

@Document(
    indexName = "traveler",
    writeTypeHint = WriteTypeHint.FALSE
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserSkill(@field:Id val userName: String, val kingdomName: String, val skillName: List<String>) {
    fun addSkillName(skillNames: List<String>): UserSkill {
        val skillName: List<String> = this.skillName + skillNames
        return this.copy(skillName = skillName)
    }

}
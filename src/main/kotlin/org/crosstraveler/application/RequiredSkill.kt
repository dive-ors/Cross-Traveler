package org.crosstraveler.application

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.WriteTypeHint

// 기능이 없어서, domain 이라 부르기 애매함
// 이름을 바꾸자 ..
@Document(
    indexName = "required-skill",
    writeTypeHint = WriteTypeHint.FALSE
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RequiredSkill(@field:Id val userName: String, val kingdomName: String, val skillName: List<String>) {
    fun addSkillName(skillNames: List<String>): RequiredSkill {
        val skillName: List<String> = this.skillName + skillNames
        return this.copy(skillName = skillName)
    }

}
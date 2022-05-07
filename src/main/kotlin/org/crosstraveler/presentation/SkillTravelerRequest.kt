package org.crosstraveler.presentation

import com.fasterxml.jackson.annotation.JsonIgnore
import org.crosstraveler.application.UserSkill


fun SkillTravelerRequest.toModel(): UserSkill {
    return UserSkill(
        userName = userName,
        kingdomName = kingdomName,
        skillName = skillName
    )
}


data class SkillTravelerRequest(var userName: String, var kingdomName: String, var skillName: List<String>) {
    @JsonIgnore
    fun isValid() = userName.isNotBlank() && skillName.isNotEmpty() && kingdomName.isNotBlank()
}
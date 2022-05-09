package org.crosstraveler.presentation.traveler

import com.fasterxml.jackson.annotation.JsonIgnore
import org.crosstraveler.application.RequiredSkill


fun RequiredSkillRequest.toModel(): RequiredSkill {
    return RequiredSkill(
        userName = userName,
        kingdomName = kingdomName,
        skillName = skillName
    )
}


data class RequiredSkillRequest(var userName: String, var kingdomName: String, var skillName: List<String>) {
    @JsonIgnore
    fun isValid() = userName.isNotBlank() && skillName.isNotEmpty() && kingdomName.isNotBlank()
}
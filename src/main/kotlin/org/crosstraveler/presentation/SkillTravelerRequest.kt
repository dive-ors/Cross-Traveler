package org.crosstraveler.presentation

import com.fasterxml.jackson.annotation.JsonIgnore
import org.crosstraveler.application.SkillTraveler


fun SkillTravelerRequest.toModel(): SkillTraveler {
    return SkillTraveler(
        userName = userName,
        kingdomName = kingdomName,
        skillName = skillName
    )
}


data class SkillTravelerRequest(var userName: String, var kingdomName: String, var skillName: List<String>) {
    @JsonIgnore
    fun isValid() = userName.isNotBlank() && skillName.isNotEmpty() && kingdomName.isNotBlank()
}
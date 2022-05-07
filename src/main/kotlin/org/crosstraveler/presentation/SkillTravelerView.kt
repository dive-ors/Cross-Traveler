package org.crosstraveler.presentation

import org.crosstraveler.application.UserSkill


fun UserSkill.toView(): SkillTravelerView {
    return SkillTravelerView(
        userName = userName,
        kingdomName = kingdomName,
        skillName = skillName
    )
}

data class SkillTravelerView(val userName: String, val kingdomName: String, val skillName: List<String>)
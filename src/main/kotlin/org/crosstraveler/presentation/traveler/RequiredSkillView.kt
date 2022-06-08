package org.crosstraveler.presentation.traveler

import org.crosstraveler.application.traveler.RequiredSkill


fun RequiredSkill.toView(): RequiredSkillView {
    return RequiredSkillView(
        userName = userName,
        kingdomName = kingdomName,
        skillName = skillName
    )
}

data class RequiredSkillView(val userName: String, val kingdomName: String, val skillName: List<String>)
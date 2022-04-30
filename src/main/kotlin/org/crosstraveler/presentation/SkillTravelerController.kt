package org.crosstraveler.presentation

import org.crosstraveler.application.SkillTravelerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SkillTravelerController(val skillTravelerService: SkillTravelerService) {

    @GetMapping("/traveler/skill")
    fun getUserBySKill(@RequestParam("skillName") skillName: String): List<SkillTravelerView> {
        require(skillName.isNotBlank()) { "userName is blank !" }

        return skillTravelerService.getPlayersBySkill(skillName).map { it.toView() }
    }

}
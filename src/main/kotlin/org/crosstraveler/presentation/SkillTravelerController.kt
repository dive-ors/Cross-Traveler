package org.crosstraveler.presentation

import org.crosstraveler.application.SkillTravelerService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SkillTravelerController(val skillTravelerService: SkillTravelerService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/traveler/skill")
    fun getUserBySKill(@RequestParam("skillName") skillName: String): List<SkillTravelerView> {
        require(skillName.isNotBlank()) { "userName is blank." }

        return skillTravelerService.getPlayersBySkill(skillName).map { it.toView() }
    }

    @PostMapping("/traveler/skill")
    fun getUserBySKill(@RequestBody skillTravelerRequest: SkillTravelerRequest): SkillTravelerView {
        require(skillTravelerRequest.isValid()) { "skillTravelerRequest is not valid. request : $skillTravelerRequest" }

        log.info(skillTravelerRequest.toString())
        val addedSkill = skillTravelerService.addTravelerNotice(skillTravelerRequest.toModel())

        return addedSkill.toView()
    }

}
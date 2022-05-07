package org.crosstraveler.presentation

import org.crosstraveler.application.SkillTravelerService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// url rule 고민 필요
@RestController
class UserWantedSkillController(val skillTravelerService: SkillTravelerService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/users/wanted-skill")
    fun getUserBySKill(@RequestParam("skillName") skillName: String): List<SkillTravelerView> {
        require(skillName.isNotBlank()) { "userName is blank." }

        return skillTravelerService.getUserByWantedSkillName(skillName).map { it.toView() }
    }

    @PostMapping("/user/wanted-skill")
    fun addUserSkill(@RequestBody skillTravelerRequest: SkillTravelerRequest): SkillTravelerView {
        require(skillTravelerRequest.isValid()) { "skillTravelerRequest is not valid. request : $skillTravelerRequest" }

        log.info(skillTravelerRequest.toString())
        val addedSkill = skillTravelerService.addUserWantedSkill(skillTravelerRequest.toModel())

        return addedSkill.toView()
    }

    @PostMapping("/user/wanted-skills")
    fun addUserSkills(@RequestBody skillTravelerRequests: List<SkillTravelerRequest>): List<SkillTravelerView> {
        // todo valid 추가

        val addedSkills = skillTravelerService.addUserWantedSkills(skillTravelerRequests.map { it.toModel() })

        return addedSkills.map { it.toView() }
    }

    // 관리자용 spring-security 고민 
    @DeleteMapping("/traveler/skill")
    fun deleteAll(): String {
        skillTravelerService.deleteAll()
        return "Success"
    }
}
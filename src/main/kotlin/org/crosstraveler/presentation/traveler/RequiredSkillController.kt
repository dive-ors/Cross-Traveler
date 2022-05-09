package org.crosstraveler.presentation.traveler

import org.crosstraveler.application.RequiredSkillService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

// url rule 고민 필요
@RestController
class RequiredSkillController(private val requiredSkillService: RequiredSkillService) {
    private val log = LoggerFactory.getLogger(javaClass)

    companion object {
        const val GET_USERS_WANTED_SKILL = "/users/required-skill"
        const val POST_USER_WANTED_SKILL = "/user/required-skill"
        const val POST_USERS_WANTED_SKILL = "/users/required-skill"
    }

    @GetMapping(GET_USERS_WANTED_SKILL)
    fun getUserByRequiredSkill(@RequestParam("skillName") skillName: String): List<RequiredSkillView> {
        require(skillName.isNotBlank()) { "userName is blank." }

        return requiredSkillService.getUserByRequiredSkill(skillName).map { it.toView() }
    }

    @PostMapping(POST_USER_WANTED_SKILL)
    fun addUserToRequiredSkill(@RequestBody requiredSkillRequest: RequiredSkillRequest): RequiredSkillView {
        require(requiredSkillRequest.isValid()) { "skillTravelerRequest is not valid. request : $requiredSkillRequest" }

        log.info(requiredSkillRequest.toString())
        val addedSkill = requiredSkillService.addUserWantedSkill(requiredSkillRequest.toModel())

        return addedSkill.toView()
    }

    @PostMapping(POST_USERS_WANTED_SKILL)
    fun addUsersToRequiredSkill(@RequestBody requiredSkillRequests: List<RequiredSkillRequest>): List<RequiredSkillView> {
        // todo valid 추가

        val addedSkills = requiredSkillService.addUserWantedSkills(requiredSkillRequests.map { it.toModel() })

        return addedSkills.map { it.toView() }
    }

    // 관리자용 spring-security 고민 
    @DeleteMapping("/users/skills")
    fun deleteAll(): String {
        requiredSkillService.deleteAll()
        return "Success"
    }
}
package org.crosstraveler.application

import org.springframework.stereotype.Service

@Service
class SkillTravelerService(private val skillTravelerRepository: SkillTravelerRepository) {

    fun getPlayersBySkill(skillName: String): List<SkillTraveler> =
        skillTravelerRepository.findBySkillNameLike(skillName)
}
package org.crosstraveler.application

import org.springframework.stereotype.Service

/**
 * TODO Service 이름을 조금 더 명확하게 바꿔야함
 */
@Service
class SkillTravelerService(private val skillTravelerRepository: SkillTravelerRepository) {

    fun getPlayersBySkill(skillName: String): List<SkillTraveler> =
        skillTravelerRepository.findBySkillNameLike(skillName)

    fun addTravelerNotice(skillTraveler: SkillTraveler): SkillTraveler {

        // 저장된 정보가 없는 경우, request 를 그대로 입력
        val user: SkillTraveler = skillTravelerRepository.findByUserName(skillTraveler.userName)
            ?: return skillTravelerRepository.save(skillTraveler)

        val notExistSkills = getNotExistSkillName(user.skillName, skillTraveler.skillName)


        return skillTravelerRepository.save(user.addSkillName(notExistSkills))

    }

    private fun getNotExistSkillName(userSkills: List<String>, addedSkills: List<String>): List<String> =
        if (userSkills.isEmpty()) {
            addedSkills
        } else {
            addedSkills.filter {
                userSkills.contains(it)
            }
        }


}
package org.crosstraveler.application

import org.springframework.stereotype.Service

/**
 * TODO Service 이름을 조금 더 명확하게 바꿔야함
 */
@Service
class SkillTravelerService(private val skillTravelerRepository: SkillTravelerRepository) {

    fun getUserByWantedSkillName(skillName: String): List<UserSkill> =
        skillTravelerRepository.findBySkillNameLike(skillName)

    fun addUserWantedSkills(userSkills: List<UserSkill>): List<UserSkill> {
        return userSkills.map { addUserWantedSkill(it) }
    }


    fun addUserWantedSkill(userSkill: UserSkill): UserSkill {
        // 저장된 정보가 없는 경우, request 를 그대로 입력
        val user: UserSkill = skillTravelerRepository.findByUserName(userSkill.userName)
            ?: return skillTravelerRepository.save(userSkill)

        val notExistSkills = getNotExistSkillName(user.skillName, userSkill.skillName)


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


    fun deleteAll() = skillTravelerRepository.deleteAll()
}
package org.crosstraveler.application

import org.springframework.stereotype.Service

@Service
class RequiredSkillService(private val requiredSkillRepository: RequiredSkillRepository) {

    fun getUserByRequiredSkills(skillNames: List<String>): List<RequiredSkill> {
        val requiredSkills = requiredSkillRepository.findBySkillNameIn(skillNames)

        // 방랑상인에 등장한 스킬들만 내려준다.
        return requiredSkills.map { it.getTravelerRequiredSkill(skillNames) }
    }

    fun addUserWantedSkills(userSkills: List<RequiredSkill>): List<RequiredSkill> {
        return userSkills.map { addUserWantedSkill(it) }
    }


    fun addUserWantedSkill(userSkill: RequiredSkill): RequiredSkill {
        // 저장된 정보가 없는 경우, request 를 그대로 입력
        val user: RequiredSkill = requiredSkillRepository.findByUserName(userSkill.userName)
            ?: return requiredSkillRepository.save(userSkill)

        val notExistSkills = getNotExistSkillName(user.skillName, userSkill.skillName)


        return requiredSkillRepository.save(user.addSkillName(notExistSkills))
    }

    private fun getNotExistSkillName(userSkills: List<String>, addedSkills: List<String>): List<String> =
        if (userSkills.isEmpty()) {
            addedSkills
        } else {
            addedSkills.filter {
                userSkills.contains(it)
            }
        }


    fun deleteAll() = requiredSkillRepository.deleteAll()
}
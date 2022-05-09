package org.crosstraveler.application

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SkillTravelerTest {

    @Test
    fun addSkillNames() {
        val userSkill = RequiredSkill("탕수륙", "숲속고양이", listOf("파이어크래커1"))

        val expect = userSkill.addSkillName(listOf("파이어익스퍼트1"))

        assertEquals(userSkill.userName, expect.userName)
        assertEquals(userSkill.kingdomName, expect.kingdomName)
        assertEquals(listOf("파이어크래커1", "파이어익스퍼트1"), expect.skillName)

    }

}
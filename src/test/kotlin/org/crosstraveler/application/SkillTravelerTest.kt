package org.crosstraveler.application

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SkillTravelerTest {

    @Test
    fun addSkillNames() {
        val skillTraveler = SkillTraveler("탕수륙", "숲속고양이", listOf("파이어크래커1"))

        val expect = skillTraveler.addSkillName(listOf("파이어익스퍼트1"))

        assertEquals(skillTraveler.userName, expect.userName)
        assertEquals(skillTraveler.kingdomName, expect.kingdomName)
        assertEquals(listOf("파이어크래커1", "파이어익스퍼트1"), expect.skillName)

    }

}
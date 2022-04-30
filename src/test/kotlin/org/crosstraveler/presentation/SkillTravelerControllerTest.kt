package org.crosstraveler.presentation

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class SkillTravelerControllerTest(val mockMvc: MockMvc) {

    @Test
    fun getUserBySkill() {
        mockMvc.perform(get("/traveler/skill").param("skillName", "파이어"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful)
    }


}
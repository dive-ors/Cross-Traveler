package org.crosstraveler.presentation

import org.crosstraveler.config.TestContainerIntegrationTest
import org.crosstraveler.util.readJson
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@TestContainerIntegrationTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserWantedSkillControllerIntegrationTest(private val mockMvc: MockMvc) {

    @Test
    @DisplayName("bulk로 원하는 스킬을 등록 후, 조회한다.")
    fun getEmptyUserBySkill() {
        val body = "/json/wanted-skill/Bulk-Wanted-Skills.json".readJson()

        mockMvc.perform(
            MockMvcRequestBuilders.post("/user/wanted-skills")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)


        mockMvc.perform(MockMvcRequestBuilders.get("/users/wanted-skill").param("skillName", "그래핑쏜2"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)


    }
}
package org.crosstraveler.presentation

import org.crosstraveler.config.TestContainerIntegrationTest
import org.crosstraveler.presentation.traveler.RequiredSkillController
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
import org.springframework.util.LinkedMultiValueMap

@AutoConfigureMockMvc
@TestContainerIntegrationTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RequiredSkillSkillControllerIntegrationTest(private val mockMvc: MockMvc) {

    /**
     * 검색 용
     * 서버를 아직 할당받지 않아서 .. 이런식으로라도 ..
     */
    @Test
    @DisplayName("bulk로 원하는 스킬을 등록 후, 조회한다.")
    fun getEmptyUserBySkill() {
        val body = "/json/wanted-skill/Bulk-Wanted-Skills.json".readJson()

        mockMvc.perform(
            MockMvcRequestBuilders.post(RequiredSkillController.POST_USERS_WANTED_SKILL)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)

        val travelerSkills = listOf("라이트익스퍼트1", "스타폴2")
        val params = LinkedMultiValueMap<String, String>()
        params.addAll("skillNames", travelerSkills)

        mockMvc.perform(MockMvcRequestBuilders.get(RequiredSkillController.GET_USERS_WANTED_SKILL).params(params))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)


    }
}
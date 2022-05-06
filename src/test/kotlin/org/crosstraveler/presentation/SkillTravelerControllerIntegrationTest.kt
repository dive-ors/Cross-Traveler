package org.crosstraveler.presentation

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.crosstraveler.application.SkillTraveler
import org.crosstraveler.application.SkillTravelerRepository
import org.crosstraveler.application.SkillTravelerService
import org.crosstraveler.util.toJson
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
internal class SkillTravelerControllerIntegrationTest(val mockMvc: MockMvc) {

    @MockkBean
    lateinit var skillTravelerService: SkillTravelerService

    @MockkBean
    lateinit var skillTravelerRepository: SkillTravelerRepository

    @Test
    @DisplayName("입력된 값이 없을 경우, 빈 응답을 내려준다.")
    fun getEmptyUserBySkill() {
        val expectSkillName = "템페스트"

        every { skillTravelerService.getPlayersBySkill(expectSkillName) } returns listOf()

        mockMvc.perform(get("/traveler/skill").param("skillName", expectSkillName))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().json("""[]"""))
    }

    @Test
    @DisplayName("입력된 값이 있을 경우, 전체 응답을 가져온다.")
    fun getUserBySkillAll() {
        val expectSkillName = "템페스트"
        val expectSkillTravelers = listOf(SkillTraveler("탕수륙", "숲속고양이", listOf("템페스트오브라이트2")))
        val expectedView = expectSkillTravelers.map { it.toView() }.toJson()

        every { skillTravelerService.getPlayersBySkill(expectSkillName) } returns expectSkillTravelers

        mockMvc.perform(get("/traveler/skill").param("skillName", expectSkillName))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().json(expectedView))
    }


    @Test
    fun addPlayer() {
        val request = SkillTravelerRequest("탕수륙", "숲속고양이", listOf("템페스트오브라이트1"))
        val skillTraveler = request.toModel()

        every { skillTravelerService.addTravelerNotice(skillTraveler) } returns skillTraveler
        mockMvc.perform(
            post("/traveler/skill")
                .content(request.toJson())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().json(skillTraveler.toView().toJson()))
    }

}
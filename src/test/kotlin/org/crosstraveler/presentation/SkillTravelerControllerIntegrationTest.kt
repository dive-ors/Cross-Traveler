package org.crosstraveler.presentation

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.crosstraveler.application.UserSkill
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

        every { skillTravelerService.getUserByWantedSkillName(expectSkillName) } returns listOf()

        mockMvc.perform(get("/users/wanted-skill").param("skillName", expectSkillName))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().json("""[]"""))
    }

    @Test
    @DisplayName("입력된 값이 있을 경우, 전체 응답을 가져온다.")
    fun getUserBySkillAll() {
        val expectSkillName = "템페스트"
        val expectUserSkills = listOf(UserSkill("탕수륙", "숲속고양이", listOf("템페스트오브라이트2")))
        val expectedView = expectUserSkills.map { it.toView() }.toJson()

        every { skillTravelerService.getUserByWantedSkillName(expectSkillName) } returns expectUserSkills

        mockMvc.perform(get("/users/wanted-skill").param("skillName", expectSkillName))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().json(expectedView))
    }


    @Test
    @DisplayName("사용자가 원하는 skill 을 추가한다.")
    fun addUserWantedSkill() {
        val request = SkillTravelerRequest("탕수륙", "숲속고양이", listOf("템페스트오브라이트1"))
        val skillTraveler = request.toModel()

        every { skillTravelerService.addUserWantedSkill(skillTraveler) } returns skillTraveler
        mockMvc.perform(
            post("/user/wanted-skill")
                .content(request.toJson())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().json(skillTraveler.toView().toJson()))
    }


    @Test
    @DisplayName("사용자가 원하는 skill들을 추가한다.")
    fun addUserWantedSkills() {
        val request = SkillTravelerRequest("탕수륙", "숲속고양이", listOf("템페스트오브라이트1"))
        val request2 = SkillTravelerRequest("후뿌뿌뿌", "숲속고양이", listOf("다크익스퍼트1"))
        val skillTravelerRequests = listOf(request, request2)
        val skillTravelers = listOf(request.toModel(), request2.toModel())

        every { skillTravelerService.addUserWantedSkills(skillTravelers) } returns skillTravelers
        mockMvc.perform(
            post("/user/wanted-skills")
                .content(skillTravelerRequests.toJson())
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().is2xxSuccessful)
                // test 에 로직이 들어간거 같아 별로임 wrapper 로 내부 로직전환?
            .andExpect(content().json(skillTravelers.map { it.toView() }.toJson()))
    }

}
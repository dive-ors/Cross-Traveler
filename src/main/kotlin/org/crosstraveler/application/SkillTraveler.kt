package org.crosstraveler.application

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

// 기능이 없어서, domain 이라 부르기 애매함

@Document(indexName = "traveler")
data class SkillTraveler(@field:Id val userName: String, val kingdomName: String, val skillName: List<String>)
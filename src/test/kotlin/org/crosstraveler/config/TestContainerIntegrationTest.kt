package org.crosstraveler.config

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles


/**
 * Elastic Config Bean 을 읽는 과정에서, connection refuse 가 발생하면서 Test 가 실패함
 * - Client Bean 을 생성하면서 request로 유효성을 확인하는거 같은데, Test 시엔 실제 API 를 호출하면 안되므로,
 * Profile 을 이용해 Bean 이 생성되지 못하게 분리한다.
 * @see ElasticsearchConfig
 */
@SpringBootTest
@ActiveProfiles("test")
@Import(ElasticsearchTestConfig::class)
annotation class TestContainerIntegrationTest


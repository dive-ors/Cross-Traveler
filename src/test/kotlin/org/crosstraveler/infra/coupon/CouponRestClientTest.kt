package org.crosstraveler.infra.coupon

import org.crosstraveler.config.TestContainerIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.test.context.TestConstructor


@TestContainerIntegrationTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class CouponRestClientTest(private val couponRestClient: CouponRestClient) {


    @Test
    fun addCoupon() {
        println(couponRestClient.addCoupon("OPGD2", "6596ABB68001469E9A93E13BC88C81CA"))
        println(couponRestClient.addCoupon("NCTJB2", "6596ABB68001469E9A93E13BC88C81CA"))
        println(couponRestClient.addCoupon("UPPREC1", "6596ABB68001469E9A93E13BC88C81CA"))
        println(couponRestClient.addCoupon("OSCCBS1", "6596ABB68001469E9A93E13BC88C81CA"))
        println(couponRestClient.addCoupon("OSCHJS1", "6596ABB68001469E9A93E13BC88C81CA"))
        println(couponRestClient.addCoupon("EDECG2", "6596ABB68001469E9A93E13BC88C81CA"))
    }

}

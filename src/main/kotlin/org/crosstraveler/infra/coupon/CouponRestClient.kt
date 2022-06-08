package org.crosstraveler.infra.coupon

import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class CouponRestClient {

    fun addCoupon(code: String, pid: String): CouponResponse {
        val restTemplate = RestTemplate()

        // MediaType.APPLICATION_FORM_URLENCODED 은 multiValueMap 을 사용해야한다.
        val request = CouponRequest(pid = pid, couponCode = code).getBody()

        val response = restTemplate.exchange(
            RequestEntity.post("https://couponview.netmarble.com/coupon/enn/1384/apply")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .headers {
                    it.add("Host", "couponview.netmarble.com")
                    it.add("Origin", "https://couponview.netmarble.com")
                    it.add("Referer", "https://couponview.netmarble.com/coupon/enn/1384")
                    it.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")
                    it.accept = listOf(MediaType.APPLICATION_JSON)
                }
                .body(request), Map::class.java
        ).body ?: throw Exception()


        // 줄일 수 있을거 같은데
        // 할게 많다
        return if (response["rewardItem"] == null) throw Exception() else CouponResponse.create(response as Map<String, Any>)
    }

}
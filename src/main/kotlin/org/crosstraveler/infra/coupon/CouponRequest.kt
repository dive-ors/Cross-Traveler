package org.crosstraveler.infra.coupon

import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

data class CouponRequest(val pid: String, val channelCode: Int = 100, val couponCode: String) {
    fun getBody(): MultiValueMap<String, String> {
        val params = LinkedMultiValueMap<String, String>()
        params.add("pid", pid)
        params.add("channelCode", channelCode.toString())
        params.add("couponCode", couponCode)
        return params
    }

}
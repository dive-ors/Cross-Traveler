package org.crosstraveler.infra.coupon

data class CouponResponse(
    val resultCode: String,
    val couponSetting: String?,
    val rewardItem: String?,
    val resultMessage: String,
    val resultSubMessage: String
) {

    companion object {

        fun create(map: Map<String, Any>): CouponResponse = CouponResponse(
            resultCode = map["resultCode"].toString(),
            couponSetting = map["couponSetting"].toString(),
            rewardItem = map["rewardItem"].toString(),
            resultMessage = map["resultMessage"].toString(),
            resultSubMessage = map["resultSubMessage"].toString()
        )

    }
}

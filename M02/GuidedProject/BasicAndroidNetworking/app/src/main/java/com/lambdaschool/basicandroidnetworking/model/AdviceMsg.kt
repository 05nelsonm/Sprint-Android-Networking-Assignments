package com.lambdaschool.basicandroidnetworking.model

import com.google.gson.annotations.SerializedName

// TODO: Define AdviceMsg and Slip classes
data class Slip(val advice: String?,
                @SerializedName ("slip_id") val slipId: String?)

data class AdviceMsg(val slip: Slip?) {
    fun getAdvice(): String? {
        return slip?.advice
    }
}

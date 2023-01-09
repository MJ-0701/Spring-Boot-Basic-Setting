package com.example.springbootapi.global.web

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ErrorResponse (
    @JsonProperty("result_code")
    var resultCode : String? = null,

    @JsonProperty("http_status")
    var httpStatus : String? = null,

    @JsonProperty("http_method")
    var httpMethod : String? = null,

    var message : String? = null,
    var path : String? = null,
    var timestamp : LocalDateTime? = null,
    var errors : MutableList<ErrorField>? = null


)

data class ErrorField(
    var field : String? = null,
    var message : String? = null,
    var value : Any? = null
)

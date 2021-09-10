package com.marvel.data.enums

enum class ApiResponseCode(val code: Int) {

    // 200
    SUCCESS(200),

    // 400
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    CONFLICT(409),

    // 500
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503)
}
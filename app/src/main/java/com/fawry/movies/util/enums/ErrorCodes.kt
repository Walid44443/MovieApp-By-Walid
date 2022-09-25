package com.fawry.movies.util.enums

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1),
    BadRequest(400),
    UnAuthorized(401),
    Forbidden(403),
    NotFound(404),
    InternalServerError(500),
    ServiceUnavailable(503),
}
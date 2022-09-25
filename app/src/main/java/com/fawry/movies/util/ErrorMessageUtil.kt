package com.fawry.movies.util

import android.content.res.Resources
import com.fawry.movies.R
import com.fawry.movies.util.enums.ErrorCodes

fun getErrorMessage(code: Int, resources: Resources): String {
    return when (code) {
        ErrorCodes.Forbidden.code -> resources.getString(R.string.forbidden)
        ErrorCodes.InternalServerError.code -> resources.getString(R.string.internal_server_error)
        ErrorCodes.BadRequest.code -> resources.getString(R.string.bad_request)
        ErrorCodes.NotFound.code -> resources.getString(R.string.not_found)
        ErrorCodes.InternalServerError.code -> resources.getString(R.string.internal_server_error)
        ErrorCodes.ServiceUnavailable.code -> resources.getString(R.string.service_unavailable)
        ErrorCodes.UnAuthorized.code -> resources.getString(R.string.un_authorized)
        ErrorCodes.SocketTimeOut.code -> resources.getString(R.string.time_out)
        else -> resources.getString(R.string.something_went_wrong)
    }
}
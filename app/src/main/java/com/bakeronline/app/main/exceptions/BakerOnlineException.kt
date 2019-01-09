package com.bakeronline.app.main.exceptions

import java.lang.Exception


open class BakerOnlineException(errorCode: ErrorCode): Exception(errorCode.message)

data class ErrorCode(val message: String = "", val code: Int? = null)
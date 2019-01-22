package com.m2f.app.main.exceptions

import java.lang.Exception


open class BakerOnlineException(errorCode: ErrorCode): Exception(errorCode.message)

data class ErrorCode(val message: String = "", val code: Int? = null)
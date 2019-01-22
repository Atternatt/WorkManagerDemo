package com.m2f.app.main.exceptions

import com.m2f.app.main.constants.ErrorCodes.UNHANDLED

/**
 * @author Marc Moreno
 */
class UnhandledException(message: String): BakerOnlineException(ErrorCode(message, UNHANDLED))
package com.bakeronline.app.main.exceptions

import com.bakeronline.app.main.constants.ErrorCodes.UNHANDLED

/**
 * @author Marc Moreno
 * @since 9/1/2019
 */
class UnhandledException(message: String): BakerOnlineException(ErrorCode(message, UNHANDLED))
package com.bakeronline.app.main.exceptions

import com.bakeronline.app.main.constants.ErrorCodes.NOT_NETWORK

/**
 * @author Marc Moreno
 * @since 9/1/2019
 */
sealed class RetryableException(errorCode: ErrorCode): BakerOnlineException(errorCode)

class NetworkTimeoutException: RetryableException(ErrorCode("No network available", NOT_NETWORK))
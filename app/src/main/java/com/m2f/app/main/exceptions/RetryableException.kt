package com.m2f.app.main.exceptions

import com.m2f.app.main.constants.ErrorCodes.NOT_NETWORK
import com.m2f.app.main.constants.ErrorCodes.RETRYABLE_CODE
import com.m2f.app.main.constants.ErrorCodes.SERVER_DOWN
import com.m2f.app.main.constants.ErrorCodes.TIMEOUT

/**
 * @author Marc Moreno
 */
sealed class RetryableException(errorCode: ErrorCode): BakerOnlineException(errorCode)

class NetworkTimeoutException: RetryableException(ErrorCode("Network Timeout", TIMEOUT))
class NoNetworkException: RetryableException(ErrorCode("No network available", NOT_NETWORK))
class ServerDownException: RetryableException(ErrorCode("Server Down", SERVER_DOWN))
class GenericRetryableException: RetryableException(ErrorCode("Retryable code from server like 500, 504 or 505", RETRYABLE_CODE))
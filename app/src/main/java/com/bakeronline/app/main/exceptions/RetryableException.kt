package com.bakeronline.app.main.exceptions

/**
 * @author Marc Moreno
 * @since 9/1/2019
 */
sealed class RetryableException: Exception()

class NetworkTimeoutException: RetryableException()
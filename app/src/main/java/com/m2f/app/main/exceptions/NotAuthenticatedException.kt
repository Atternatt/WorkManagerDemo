package com.m2f.app.main.exceptions

import com.m2f.app.main.constants.ErrorCodes.NOT_AUTHENTICATED

/**
 * @author Marc Moreno
 */
class NotAuthenticatedException : BakerOnlineException(ErrorCode("Not Authenticated", NOT_AUTHENTICATED))
package com.bakeronline.app.main.exceptions

import com.bakeronline.app.main.constants.ErrorCodes.NOT_AUTHENTICATED

/**
 * @author Marc Moreno
 * @since 9/1/2019
 */
class NotAuthenticatedException : BakerOnlineException(ErrorCode("Not Authenticated", NOT_AUTHENTICATED))
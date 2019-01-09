package com.bakeronline.app.main.exceptions

import com.bakeronline.app.main.constants.ErrorCodes.NO_RESULTS

/**
 * @author Marc Moreno
 * @since 9/1/2019
 */
class NoResultsException: BakerOnlineException(ErrorCode("No results found", NO_RESULTS))
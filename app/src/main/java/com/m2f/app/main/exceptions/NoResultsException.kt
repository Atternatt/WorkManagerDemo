package com.m2f.app.main.exceptions

import com.m2f.app.main.constants.ErrorCodes.NO_RESULTS

/**
 * @author Marc Moreno
 */
class NoResultsException: BakerOnlineException(ErrorCode("No results found", NO_RESULTS))
package com.m2f.app.domain.executor

import java.util.concurrent.Executor

/**
 * @author Marc Moreno
 *
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * @see io.folioapp.android.domain.interactor.BaseInteractor out of the UI thread.
 */
interface ThreadExecutor:Executor
package com.m2f.app.domain.pagination

import com.m2f.app.data.pagination.RandomUUIDGenerator
import com.m2f.app.domain.Interactor
import com.m2f.app.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject


class GetUUIDsInteractor
@Inject constructor(
    private val executor: ThreadExecutor,
    private val randomUUIDGenerator: RandomUUIDGenerator
) : Interactor {

    //for every new page, provide a new list of UUIDs
    operator fun invoke(page: Flowable<Int>): Flowable<List<UUID>> =
        page.flatMap { randomUUIDGenerator.generateItems() }
            .addThreadPolicy(executor)
}
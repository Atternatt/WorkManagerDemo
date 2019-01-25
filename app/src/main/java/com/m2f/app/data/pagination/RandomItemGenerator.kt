package com.m2f.app.data.pagination

import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject


class RandomUUIDGenerator
@Inject constructor(private val numberOfItems: Int) {
    fun generateItems(): Flowable<List<UUID>> = Flowable.just((0..numberOfItems).map { UUID.randomUUID() })
}
package com.m2f.app.presentation.pagination.simple

import androidx.databinding.ObservableField
import com.m2f.app.domain.pagination.GetUUIDsInteractor
import com.m2f.app.main.DisposableViewModel
import com.m2f.app.main.utils.extensions.observe
import com.m2f.app.presentation.pagination.PartialUUIDListViewState
import com.m2f.app.presentation.pagination.UUIDListViewState
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class SimplePaginationViewModel
@Inject constructor(randomInteractor: GetUUIDsInteractor) : DisposableViewModel() {

    //this observableField is binded to a endless scroll recyclerview.
    val page: ObservableField<Int> = ObservableField(0)

    val viewState = ObservableField<UUIDListViewState>(
        UUIDListViewState(
            list = listOf(),
            numberOfPagesLoaded = 0
        )
    )

    init {
        compositeDisposable += randomInteractor(page.observe())
            .map { uuidList -> PartialUUIDListViewState(uuidList) }
            .scan(viewState.get()!!) { state, partialState ->
                state.copy(
                    list = state.list + partialState.list,
                    numberOfPagesLoaded = state.numberOfPagesLoaded + 1
                )
            }
            .subscribeBy { viewState.set(it) }
    }
}
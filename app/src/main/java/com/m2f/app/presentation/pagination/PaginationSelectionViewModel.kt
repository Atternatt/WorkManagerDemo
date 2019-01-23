package com.m2f.app.presentation.pagination

import androidx.lifecycle.ViewModel
import com.m2f.app.main.codes.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class PaginationSelectionViewModel
@Inject constructor(private val router: Router) : ViewModel() {

    fun toSimplePagination() {
        router.navigateTo(Screens.SimplePaginationScreen)
    }

    fun toAdvancedPagination() {
        router.navigateTo(Screens.AdvancedPaginationScreen)
    }

    fun toJetpakPagination() {
        router.navigateTo(Screens.JetpackPaginationScreen)
    }
}
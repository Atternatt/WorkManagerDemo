package com.m2f.app.main.codes

import android.content.Context
import android.content.Intent
import com.m2f.app.presentation.pagination.AdvancedPaginationActivity
import com.m2f.app.presentation.pagination.JetpackPaginationActivity
import com.m2f.app.presentation.pagination.PaginationSelectionActivity
import com.m2f.app.presentation.pagination.SimplePaginationActivity
import ru.terrakok.cicerone.android.pure.AppScreen


internal object Screens {

    object PaginationSelectionScreen : AppScreen() {
        override fun getActivityIntent(context: Context?): Intent =
            Intent(context, PaginationSelectionActivity::class.java)
    }

    object SimplePaginationScreen : AppScreen() {
        override fun getActivityIntent(context: Context?): Intent =
            Intent(context, SimplePaginationActivity::class.java)
    }

    object AdvancedPaginationScreen : AppScreen() {
        override fun getActivityIntent(context: Context?): Intent =
            Intent(context, AdvancedPaginationActivity::class.java)
    }

    object JetpackPaginationScreen : AppScreen() {
        override fun getActivityIntent(context: Context?): Intent =
            Intent(context, JetpackPaginationActivity::class.java)
    }
}
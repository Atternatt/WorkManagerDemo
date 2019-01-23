package com.m2f.app.presentation.pagination

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.m2f.app.R
import com.m2f.app.databinding.ActivityPaginationSelectionBinding
import com.m2f.app.main.NavigableActivity

class PaginationSelectionActivity : NavigableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil
            .setContentView<ActivityPaginationSelectionBinding>(this, R.layout.activity_pagination_selection).apply {
                vm = getViewModel()
            }
    }
}

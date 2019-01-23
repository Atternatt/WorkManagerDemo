package com.m2f.app.presentation.pagination.simple

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.m2f.app.R
import com.m2f.app.databinding.ActivitySimplePaginationBinding
import com.m2f.app.main.BaseActivity

class SimplePaginationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivitySimplePaginationBinding>(this, R.layout.activity_simple_pagination)
            .apply {
                vm = getViewModel()
            }
    }
}

package com.m2f.app.main

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.m2f.app.di.Injectable
import javax.inject.Inject

/**
 * @author Marc Moreno
 */
abstract class BaseDialogFragment : DialogFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified VM : ViewModel> getViewModel(): VM =
        ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)

}
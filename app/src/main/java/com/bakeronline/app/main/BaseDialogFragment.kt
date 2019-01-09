package com.bakeronline.app.main

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bakeronline.app.di.Injectable
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Marc Moreno
 * @since 15/11/17.
 */
abstract class BaseDialogFragment : DialogFragment(), Injectable {

    @Inject
    lateinit var mainRouter: Router

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified VM : ViewModel> getViewModel(): VM =
        ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)

}
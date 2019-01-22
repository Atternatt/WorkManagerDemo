package com.m2f.app.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.m2f.app.di.Injectable
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@SuppressLint("Registered")
class BaseActivity: AppCompatActivity(), Injectable {

    @Inject
    lateinit var mainRouter: Router

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified VM : ViewModel> getViewModel(): VM = ViewModelProviders.of(this, viewModelFactory).get(VM::class.java)
}
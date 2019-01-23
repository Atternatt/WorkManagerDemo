package com.m2f.app.main

import com.m2f.app.di.NavigationModule
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject


abstract class NavigableActivity : BaseActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var navigatorFactory: NavigationModule.NavigatorFactory

    private val navigator: Navigator by lazy { navigatorFactory.createNavigator(this, 0) }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
    }
}
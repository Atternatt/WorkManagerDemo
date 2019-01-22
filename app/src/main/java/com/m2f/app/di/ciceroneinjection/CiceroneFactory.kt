package com.m2f.app.di.ciceroneinjection

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Marc Moreno
 */
@Singleton
class CiceroneFactory
@Inject constructor() {

    private val map: MutableMap<String, Cicerone<Router>> = mutableMapOf()

    fun getCicerone(name: String): Cicerone<Router> = map[name] ?: Cicerone.create().apply { map[name] = this }

    fun clear() {
        map.clear()
    }
}
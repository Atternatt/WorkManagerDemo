package com.m2f.app.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

internal fun Application.initInjection() {

    this.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

        //region not implemented
        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityStarted(activity: Activity?) {
        }

        override fun onActivityDestroyed(activity: Activity?) {
        }

        override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }
        //endregion

        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            if (activity is Injectable) {
                AndroidInjection.inject(activity)
                if (activity is AppCompatActivity) {
                    activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentCreated(
                                fm: FragmentManager,
                                f: Fragment,
                                savedInstanceState: Bundle?
                            ) {
                                if (f is Injectable) {
                                    AndroidSupportInjection.inject(f)
                                }
                            }
                        }, true
                    )
                }
            }
        }

    })
}
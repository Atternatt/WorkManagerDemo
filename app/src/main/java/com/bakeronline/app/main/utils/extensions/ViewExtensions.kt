package com.bakeronline.app.main.utils.extensions

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Service
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Marc Moreno
 * @since 9/1/2019.
 */

fun View.onClick(click: (View) -> Unit) {
    this.setOnClickListener(click)
}

fun View.bounce() {
    val animX = ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.1f, 1f)
    val animY = ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.1f, 1f)

    AnimatorSet().apply {
        playTogether(animX, animY)
        duration = 350
        interpolator = BounceInterpolator()
    }.start()
}

fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromInputMethod(this.windowToken, 0)
}

private fun backgroundColorAnimator(): ReadWriteProperty<View, Int> =
    object : ReadWriteProperty<View, Int> {

        override fun getValue(thisRef: View, property: KProperty<*>): Int {
            return (thisRef.background as? ColorDrawable)?.color ?: 0
        }

        override fun setValue(thisRef: View, property: KProperty<*>, value: Int) {

            ValueAnimator.ofObject(ArgbEvaluator(), getValue(thisRef, property), value).apply {
                duration = 1000L
                interpolator = AccelerateDecelerateInterpolator()
                addUpdateListener { thisRef.setBackgroundColor(it.animatedValue as Int) }
            }.start()

        }

    }

val View.bgColor: Int by backgroundColorAnimator()

var ImageView.imageAlfa: Int by alphaWithBounceAnimator()

private fun alphaWithBounceAnimator(): ReadWriteProperty<ImageView, Int> =
    object : ReadWriteProperty<ImageView, Int> {

        override fun getValue(thisRef: ImageView, property: KProperty<*>): Int {
            return thisRef.imageAlpha
        }

        override fun setValue(thisRef: ImageView, property: KProperty<*>, value: Int) {

            val animX = ObjectAnimator.ofFloat(thisRef, "scaleX", 1f, 1.25f, 1f)
            val animY = ObjectAnimator.ofFloat(thisRef, "scaleY", 1f, 1.25f, 1f)
            val alpha = ObjectAnimator.ofInt(thisRef.imageAlpha, value).apply {
                addUpdateListener { thisRef.imageAlpha = it.animatedValue as Int }
            }

            if (thisRef.imageAlpha != value) {
                AnimatorSet().apply {
                    playTogether(animX, animY, alpha)
                    duration = 150L
                    interpolator = AccelerateDecelerateInterpolator()
                }.start()
            }
        }

    }
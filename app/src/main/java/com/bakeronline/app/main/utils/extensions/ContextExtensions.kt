package com.bakeronline.app.main.utils.extensions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bakeronline.app.R
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


/**
 * @author Marc Moreno
 * @since 8/1/2019.
 */

fun Context.getColorWithId(id: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) getColor(id)
    else resources.getColor(id)
}

fun Activity.requestPermission(permission: String, permissionResponse: (accepted: Boolean) -> Unit) {
    Dexter.withActivity(this)
        .withPermission(permission)
        .withListener(object : PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse) {
                permissionResponse(true)
            }

            override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {
                token.continuePermissionRequest()

            }

            override fun onPermissionDenied(response: PermissionDeniedResponse) {
                permissionResponse(false)
            }
        }).check()
}

fun Fragment.createErrorSnackbar(@StringRes message: Int) =
    kotlin.with(view!!) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
            .apply {
                (view as? Snackbar.SnackbarLayout)?.apply {
                    setBackgroundResource(R.color.errorColor)
                }

            }
    }

fun Activity.createErrorSnackbar(@StringRes message: Int, onView: View) =
    kotlin.with(onView) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
            .apply {
                (view as? Snackbar.SnackbarLayout)?.apply {
                    setBackgroundResource(R.color.errorColor)
                }

            }
    }

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)

fun Activity.showInfo(
    @StringRes resourceId: Int,
    negativeExecutionBlock: (() -> Unit)? = null,
    positiveExecutionBlock: (() -> Unit)? = null
) {
    showInfo(getString(resourceId, negativeExecutionBlock, positiveExecutionBlock))
}

fun Activity.showInfo(
    message: String,
    negativeExecutionBlock: (() -> Unit)? = null,
    positiveExecutionBlock: (() -> Unit)? = null
) {
    AlertDialog.Builder(this, R.style.DialogAnimation)
        .setMessage(message)
        .apply {
            setPositiveButton("OK") { _, _ -> positiveExecutionBlock?.invoke() }
            if (negativeExecutionBlock != null) setNegativeButton(R.string.cancel) { _, _ -> negativeExecutionBlock() }
        }
        .create()
        .apply {
            setOnShowListener { getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.color(R.color.errorColor)) }
        }
        .show()
}

fun Fragment.showInfo(
    message: String,
    negativeExecutionBlock: (() -> Unit)? = null,
    positiveExecutionBlock: (() -> Unit)? = null
) {
    AlertDialog.Builder(this.activity!!, R.style.DialogAnimation)
        .setMessage(message)
        .apply {
            setPositiveButton("OK") { _, _ -> positiveExecutionBlock?.invoke() }
            if (negativeExecutionBlock != null) setNegativeButton(R.string.cancel) { _, _ -> negativeExecutionBlock() }
        }
        .create()
        .apply {
            setOnShowListener { getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.color(R.color.errorColor)) }
        }
        .show()
}

fun Fragment.showInfo(
    @StringRes resourceId: Int,
    negativeExecutionblock: (() -> Unit)? = null,
    positiveExecutionBlock: (() -> Unit)? = null
) {
    showInfo(
        getString(resourceId),
        negativeExecutionblock,
        positiveExecutionBlock
    )
}

fun Context.getStringResourceByName(resourceName: String): String =
    getString(resources.getIdentifier(resourceName, "string", packageName))
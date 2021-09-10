package com.marvel.characters.utils.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.databinding.OnRebindCallback
import androidx.databinding.ViewDataBinding
import androidx.transition.TransitionManager
import com.marvel.characters.R
import com.marvel.characters.utils.bindingproperties.ActivityBindingProperty

inline fun <reified T : ViewDataBinding> T.animateTransitionOnRebind() {
    addOnRebindCallback(object : OnRebindCallback<T>() {
        override fun onPreBind(binding: T?): Boolean {
            TransitionManager.beginDelayedTransition(binding?.root as ViewGroup)
            return super.onPreBind(binding)
        }
    })
}

fun <T : ViewDataBinding> activityBinding(@LayoutRes resId: Int) =
    ActivityBindingProperty<T>(resId)

fun Context.isConnected(): Boolean {
    val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network: Network? = connMgr.activeNetwork
    val networkCapabilities: NetworkCapabilities? = connMgr.getNetworkCapabilities(network)
    networkCapabilities?.let {
        return (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
    } ?: run {
        return false
    }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.hideKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.run { hideKeyboard(this) }
}

@Suppress("DEPRECATION")
@SuppressLint("PrivateResource")
fun ActionBar.setBackIconColor(@ColorRes color: Int) {
    val drawable = ContextCompat.getDrawable(this.themedContext, R.drawable.abc_ic_ab_back_material)
    drawable?.setColorFilter(ContextCompat.getColor(this.themedContext, color), android.graphics.PorterDuff.Mode.SRC_ATOP)
    this.setHomeAsUpIndicator(drawable)
}

inline fun <reified T : Any> newIntent(
    context: Context,
    noinline init: Intent.() -> Unit = {}
): Intent {
    val intent = Intent(context, T::class.java)
    intent.init()
    return intent
}

inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    startActivityForResult(newIntent<T>(this, init), requestCode, options)
}

inline fun <reified T : Any> Activity.launchActivityForSharedElements(
    args: Bundle? = null,
    vararg pairs: Pair<View, String>,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this, init)
    args?.run {
        intent.putExtras(args)
    }

    val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
        this,
        *pairs
    )
    startActivity(intent, options.toBundle())
}
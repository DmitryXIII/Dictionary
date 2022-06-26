package com.ineedyourcode.core.ui.uils

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.ineedyourcode.core.R
import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

class ViewByIdDelegate<out T : View>(private val rootGetter: () -> View?, private val viewId: Int) {
    private var rootRef: WeakReference<View>? = null
    private var viewRef: T? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewRef
        val cachedRoot = rootRef?.get()
        val currentRoot = rootGetter()

        if (currentRoot != cachedRoot || view == null) {
            if (currentRoot == null) {
                if (view != null) {
                    return view
                }
                throw IllegalStateException(viewRef?.context?.getString(R.string.view_dont_created))
            }
            view = currentRoot.findViewById(viewId)
            viewRef = view
            rootRef = WeakReference(currentRoot)
        }

        if (view == null) {
            throw IllegalStateException(view?.context?.getString(R.string.cant_find_view_id_in_root,
                viewId))
        }
        return view
    }
}

fun <T : View> Fragment.viewById(@IdRes viewId: Int): ViewByIdDelegate<T> {
    return ViewByIdDelegate({ view }, viewId)
}
package com.seblacity.design.pager

import android.os.Bundle
import android.support.annotation.AnimRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.ViewGroup
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * Created by Onur on 21.8.2018.
 */
open class FragmentPagerAdapter(private val container: ViewGroup, private val fragmentManager: FragmentManager) {
    private val fragments = hashMapOf<Int, FragmentInfo>()
    private var listeners = arrayListOf<OnPageChangeListener>()

    var currentPage: Int = -1
        private set(value) {
            field = value
        }

    val currentPageFragment get() = fragments[currentPage]?.fragment!!

    fun addItem(fragmentInfo: FragmentInfo) {
        fragments[fragmentInfo.id] = fragmentInfo
    }

    fun getPageTitle(id: Int): String {
        if (fragments[id]?.title != null) {
            return fragments[id]?.title!!
        }

        if (fragments[id]?.titleResId != -1) {
            return container.context.getString(fragments[id]?.titleResId!!)
        }

        return ""
    }

    fun change(id: Int, arguments: Bundle? = null) {
        val info = fragments[id]!!
        val transaction = fragmentManager.beginTransaction()
        val name = "android:fragmentPager:${container.id}:$id"

        if (info.fragment != null) {
            transaction.show(info.fragment)
        } else {
            info.fragment = info.fragmentClass.createInstance()

            transaction.add(container.id, info.fragment, name)
        }

        transaction.setCustomAnimations(info.enterAnim, info.exitAnim)

        if (currentPage != -1) {
            val current = fragments[currentPage]!!

            current.fragment?.setMenuVisibility(false)
            current.fragment?.userVisibleHint = false

            if (!current.cacheEnabled) {
                transaction.remove(current.fragment)

                current.fragment = null
            } else {
                transaction.hide(current.fragment)
            }
        }

        info.fragment?.arguments = arguments
        info.fragment?.setMenuVisibility(true)
        info.fragment?.userVisibleHint = true

        currentPage = id

        transaction.commitNowAllowingStateLoss()

        dispatchOnPageChanged(id)
    }

    fun addOnPageChangeListener(listener: OnPageChangeListener) {
        listeners.add(listener)
    }

    private fun dispatchOnPageChanged(id: Int) {
        listeners.forEach { it.onPageChanged(id) }
    }

    class FragmentInfo(val id: Int, val fragmentClass: KClass<out Fragment>) {
        internal var title: String? = null
        internal var titleResId: Int = -1
        internal var enterAnim: Int = 0
        internal var exitAnim: Int = 0
        internal var cacheEnabled: Boolean = false
        internal var fragment: Fragment? = null

        fun withTitle(title: String) = apply { this.title = title }

        fun withTitle(@StringRes titleResId: Int) = apply { this.titleResId = titleResId }

        fun withEnterAnim(@AnimRes enterAnim: Int) = apply { this.enterAnim = enterAnim }

        fun withExitAnim(@AnimRes exitAnim: Int) = apply { this.exitAnim = exitAnim }

        fun withCacheEnabled(cacheEnabled: Boolean) = apply { this.cacheEnabled = cacheEnabled }
    }

    interface OnPageChangeListener {
        fun onPageChanged(id: Int)
    }
}
package com.immediate.functiontest

import android.view.View

/**
 * PackageName : com.immediate.functiontest <br/>
 *
 * Creator : sun <br/>
 *
 * CreateDate : 2019-11-27 <br/>
 *
 * CreateTime : 16 : 58 <br/>
 *
 * Description :
 */

//防止连点的时间间隔
private var <T : View> T.triggerDelay: Long
    get() = if (getTag(1123461123) != null) getTag(1123461123) as Long else -1
    set(value) {
        setTag(1123461123, value)
    }

//最后点击的时间
private var <T : View> T.triggerLastTime: Long
    get() = if (getTag(1123460103) != null) getTag(1123460103) as Long else -1
    set(value) {
        setTag(1123460103, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var isClickable = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        isClickable = true
    }
    triggerLastTime = currentClickTime
    return isClickable
}

fun <T : View> T.click(listener: (T) -> Unit) {
    return setOnClickListener {
        if (clickEnable()) listener(it as T)
    }

}

fun <T : View> T.clickWithTrigeger(time: Long = 1000, listener: (T) -> Unit) {
    triggerDelay = time
    return setOnClickListener {
        if (clickEnable()) listener(it as T)
    }
}









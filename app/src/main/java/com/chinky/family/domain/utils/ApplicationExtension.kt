package com.chinky.family.domain.utils

fun Class<*>.printLogcat(element: StackTraceElement?, message: String?) {
    Utility.printLog(this, element, message)
}

inline fun Class<*>.printLogcat(message: String?) {
    Utility.printLog(this, Thread.currentThread().stackTrace[2], message)
}
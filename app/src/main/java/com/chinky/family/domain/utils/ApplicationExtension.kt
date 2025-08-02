package com.chinky.family.domain.utils

fun Class<*>.printLogcat(element: StackTraceElement?, message: String?) {
    Utility.printLog(this, element, message)
}
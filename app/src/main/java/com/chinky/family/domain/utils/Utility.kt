package com.chinky.family.domain.utils

object Utility {
    private const val TAG = "ApplicationLog"
    fun <T> printLog(klass: Class<T>, element: StackTraceElement?, message: String?) {
        element?.run {
            val prefix = "$TAG :: ${klass.simpleName} :: $lineNumber :: $methodName"
            println(message?.let { "$prefix :: $it" } ?: prefix)
        }
    }
}
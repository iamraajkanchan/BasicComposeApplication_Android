package com.chinky.family.domain.utils

object Utility {
    private const val TAG = "ApplicationLog"
    fun <T> printLog(klass: Class<T>, element: StackTraceElement?, message: String) {
        element?.let { e ->
            println("$TAG :: ${klass.simpleName} :: ${e.lineNumber} :: ${e.methodName} :: $message")
        }
    }
}
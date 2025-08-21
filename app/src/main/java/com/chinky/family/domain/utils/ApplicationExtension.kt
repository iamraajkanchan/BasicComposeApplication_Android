package com.chinky.family.domain.utils

fun Class<*>.printLogcat(message: String?) {
    val stackTrace = Thread.currentThread().stackTrace
    // index = 0 -> Thread.getStackTrace, index = 1 -> Class.printLogcat, index = 2 -> sometimes the inlined site/compiler - generated wrapper, index = 3 -> Your actual caller (e.g. MainActivity.onCreate)
    val quickPick = stackTrace.getOrNull(3)
    val element = if (quickPick != null &&
        quickPick.className != Thread::class.java.name &&
        !quickPick.className.contains("Utility") &&
        !quickPick.className.contains("printLogcat")
    ) {
        quickPick
    } else {
        stackTrace.firstOrNull {
            it.className != Thread::class.java.name &&
                    !it.className.contains("Utility") &&
                    !it.className.contains("printLogcat")
        }
    }
    Utility.printLog(this,element, message)
}



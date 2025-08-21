package com.chinky.family.presentation.ui.services.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.chinky.family.domain.utils.ApplicationConstant
import com.chinky.family.domain.utils.Utility
import com.chinky.family.domain.utils.printLogcat

class DemoBackgroundService : Service() {

    override fun onCreate() {
        super.onCreate()
        DemoBackgroundService::class.java.printLogcat("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        DemoBackgroundService::class.java.printLogcat("onStartCommand")
        printLogFromService(intent)
        return START_STICKY
    }

    private fun printLogFromService(intent: Intent?) {
        intent?.let { i ->
            val intentString =
                i.getStringExtra(ApplicationConstant.ARGUMENT_DEMO_BACKGROUND_SERVICE_MAIN_DATA)
            for (j in 1..10) {
                DemoBackgroundService::class.java.printLogcat("$j : $intentString")
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        DemoBackgroundService::class.java.printLogcat("onBind")
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        DemoBackgroundService::class.java.printLogcat("onDestroy")
    }
}
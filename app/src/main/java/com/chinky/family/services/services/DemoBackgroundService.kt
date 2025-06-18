package com.chinky.family.services.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.chinky.family.utils.ApplicationConstant
import com.chinky.family.utils.Utility

class DemoBackgroundService : Service() {

    override fun onCreate() {
        super.onCreate()
        Utility.printLog(
            DemoBackgroundService::class.java,
            Thread.currentThread().stackTrace[2],
            "onCreate"
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Utility.printLog(
            DemoBackgroundService::class.java,
            Thread.currentThread().stackTrace[2],
            "onStartCommand"
        )
        printLogFromService(intent)
        return START_STICKY
    }

    private fun printLogFromService(intent: Intent?) {
        intent?.let { i ->
            val intentString =
                i.getStringExtra(ApplicationConstant.ARGUMENT_DEMO_BACKGROUND_SERVICE_MAIN_DATA)
            for (j in 1..10) {
                Utility.printLog(DemoBackgroundService::class.java, Thread.currentThread().stackTrace[2], "$j : $intentString")
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Utility.printLog(
            DemoBackgroundService::class.java,
            Thread.currentThread().stackTrace[2],
            "onBind"
        )
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Utility.printLog(
            DemoBackgroundService::class.java,
            Thread.currentThread().stackTrace[2],
            "onDestroy"
        )
    }
}
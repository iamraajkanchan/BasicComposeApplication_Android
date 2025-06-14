package com.chinky.family.services.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class DemoBoundService : Service() {

    private val binder = DemoBoundServiceBinder()
    private var activityData: String? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder = binder

    inner class DemoBoundServiceBinder() : Binder() {
        fun getDemoBoundService(): DemoBoundService = this@DemoBoundService
    }

    fun getActivityData() : String? = this.activityData

    fun setActivityData(activityData: String) {
        this.activityData = activityData
    }
}
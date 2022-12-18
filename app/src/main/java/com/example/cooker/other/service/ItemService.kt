package com.example.cooker.other.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.cooker.other.managers.NotificationsManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemService: Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationsManager.background(this)
        startForeground(1, notification)
        startService()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startService() {
        GlobalScope.launch {
            while (true) {
                Thread.sleep(5000)
            }
        }
    }
}
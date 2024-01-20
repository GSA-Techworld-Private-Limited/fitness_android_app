package com.fitness.app.modules.responses

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ExoService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Perform your background tasks here

        // If you want the service to continue running until explicitly stopped:
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // Perform any cleanup here
    }
}

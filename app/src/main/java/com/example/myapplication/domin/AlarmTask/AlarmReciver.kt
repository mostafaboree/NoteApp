package com.example.myapplication.domin.AlarmTask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
const val MESSAGE_EXTRA="message_extra"
class AlarmReciver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val massage=intent?.getStringExtra(MESSAGE_EXTRA) ?: return
        println("Alarm triggered: $massage")
    }
}
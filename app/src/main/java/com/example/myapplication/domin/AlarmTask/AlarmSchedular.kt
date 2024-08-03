package com.example.myapplication.domin.AlarmTask

interface AlarmSchedular {
    fun addAlarm(alarm: AlarmNote)
    fun cancel(alarm: AlarmNote)
}
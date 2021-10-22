package com.zidan.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Alarm::class],
    version = 1
)

abstract class AlarmDB : RoomDatabase() {

    abstract fun alarmDAO() : AlarmDAO

    companion object {

        @Volatile private var instance : AlarmDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AlarmDB::class.java,
            "Alarms.db"
        ).build()
    }
}
package com.zidan.myapplication.room

import androidx.room.*


@Dao
interface AlarmDAO {

    @Insert
    suspend fun addAlarm(alarm: Alarm)

    @Update
    suspend fun updateAlarm(alarm: Alarm)

    @Delete
    suspend fun deleteAlarm(alarm: Alarm)

    @Query ("select * from alarm where hari = 1")
    suspend fun getSundayAlarm():List<Alarm>

    @Query ("select * from alarm where hari = 2")
    suspend fun getMondayAlarm():List<Alarm>

    @Query ("select * from alarm where hari = 3")
    suspend fun getTuesdayAlarm():List<Alarm>

    @Query ("select * from alarm where hari = 4")
    suspend fun getWednesdayAlarm():List<Alarm>

    @Query ("select * from alarm where hari = 5")
    suspend fun getThursdayAlarm():List<Alarm>

    @Query ("select * from alarm where hari = 6")
    suspend fun getFridayAlarm():List<Alarm>

    @Query ("select * from alarm where hari = 7")
    suspend fun getSaturdayAlarm():List<Alarm>
}
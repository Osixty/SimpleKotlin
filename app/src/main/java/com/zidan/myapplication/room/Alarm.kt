package com.zidan.myapplication.room

import android.os.Parcelable
import android.widget.TimePicker
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Time
import java.time.DayOfWeek
import java.util.*

@Parcelize
@Entity
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val jam : Int,
    val menit : Int,
    val hari : Int,
    val judul_alarm : String,
    val detail_alarm : String
) : Parcelable

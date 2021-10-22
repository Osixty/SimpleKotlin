package com.zidan.myapplication

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.zidan.myapplication.room.Alarm
import com.zidan.myapplication.room.AlarmDB
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {

    val db by lazy { AlarmDB(this) }
    lateinit var alarmAdapter: AlarmAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val alarm = intent.getParcelableExtra<Alarm>(MainActivity.INTENT_PARCELABLE)

        val clock = findViewById<TimePicker>(R.id.edit_time)
        val judul = findViewById<EditText>(R.id.edit_j_alarm)
        val detil = findViewById<EditText>(R.id.edit_d_alarm)

        clock.setIs24HourView(true)
        clock.hour = alarm!!.jam
        clock.minute = alarm.menit
        judul.setText(alarm.judul_alarm)
        detil.setText(alarm.detail_alarm)

        val updateButton = findViewById<Button>(R.id.update_routine)
        updateButton.setOnClickListener{
            val judul_alarm = judul.text.toString()
            val detil_alarm = detil.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                db.alarmDAO().updateAlarm(
                    Alarm(alarm.id, clock.hour, clock.minute, alarm.hari, judul_alarm, detil_alarm)
                )
                finish()
            }
            val toast = Toast.makeText(this, "Alarm Telah diperbarui", Toast.LENGTH_SHORT).show()
        }

        val deleteButton = findViewById<Button>(R.id.delete_routine)
        deleteButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.alarmDAO().deleteAlarm(
                    Alarm(alarm.id, alarm.jam, alarm.menit, alarm.hari, alarm.judul_alarm, alarm.detail_alarm)
                )
                finish()
            }
            val toast = Toast.makeText(this, "Alarm telah dihapus", Toast.LENGTH_SHORT).show()
        }
    }
}
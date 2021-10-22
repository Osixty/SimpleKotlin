package com.zidan.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import com.zidan.myapplication.room.Alarm
import com.zidan.myapplication.room.AlarmDB
import kotlinx.android.synthetic.main.activity_routin.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RoutineActivity : AppCompatActivity() {

    val db by lazy { AlarmDB(this) }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routin)
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.kuning)
        }

        val clock = findViewById<TimePicker>(R.id.timePicker)
        clock.setIs24HourView(true)

        val addRoutine = findViewById<Button>(R.id.add_routine)
        addRoutine.setOnClickListener {
            val jam = clock.hour
            val menit = clock.minute
            val hari: Int = intent.getIntExtra("day", 0)
            val judul = findViewById<EditText>(R.id.input_j_alarm).text.toString()
            val detil = findViewById<EditText>(R.id.input_d_alarm).text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                db.alarmDAO().addAlarm(
                    Alarm(0, jam, menit, hari, judul, detil)
                )
                finish()
            }
            val toast = Toast.makeText(this, "Alarm telah ditambahkan", Toast.LENGTH_SHORT).show()
        }

    }
}
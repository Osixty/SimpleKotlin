package com.zidan.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zidan.myapplication.room.Alarm
import com.zidan.myapplication.room.AlarmDB
import kotlinx.android.synthetic.main.activity_monday.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class MondayActivity : AppCompatActivity() {

    val db by lazy { AlarmDB(this) }
    lateinit var alarmAdapter: AlarmAdapter

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monday)

        val addroutinebutton = findViewById<AppCompatButton>(R.id.add_monday)
        addroutinebutton.setOnClickListener {
            val intent = Intent(this, RoutineActivity::class.java)
            intent.putExtra("day", Calendar.MONDAY)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val alarms = db.alarmDAO().getMondayAlarm()
            withContext(Dispatchers.Main){
                alarmAdapter.setData(alarms)
            }
        }

        alarmAdapter = AlarmAdapter(arrayListOf()){
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
        monday_viewer.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = alarmAdapter
        }
    }
}
package com.zidan.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zidan.myapplication.room.AlarmDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.TimeZone;

class MainActivity : AppCompatActivity() {

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Panggil method pembuat recycleView, biar aplikasi gak nge-crash
        setupRecycler()

        val sundaybutton = findViewById<LinearLayout>(R.id.sunday)
        sundaybutton.setOnClickListener {
            val intent = Intent(this, SundayActivity::class.java)
            startActivity(intent)
        }
        val mondaybutton = findViewById<LinearLayout>(R.id.Monday)
        mondaybutton.setOnClickListener {
            val intent = Intent(this, MondayActivity::class.java)
            startActivity(intent)
        }
        val tuesdaybutton = findViewById<LinearLayout>(R.id.Tuesday1)
        tuesdaybutton.setOnClickListener {
            val intent = Intent(this, TuesdayActivity::class.java)
            startActivity(intent)
        }
        val wednesdaybutton = findViewById<LinearLayout>(R.id.wednesday1)
        wednesdaybutton.setOnClickListener {
            val intent = Intent(this, WednesdayActivity::class.java)
            startActivity(intent)
        }
        val thursdaybutton = findViewById<LinearLayout>(R.id.Thursday1)
        thursdaybutton.setOnClickListener {
            val intent = Intent(this, ThursdayActivity::class.java)
            startActivity(intent)
        }
        val fridaybutton = findViewById<LinearLayout>(R.id.friday1)
        fridaybutton.setOnClickListener {
            val intent = Intent(this, FridayActivity::class.java)
            startActivity(intent)
        }
        val saturdaybutton = findViewById<LinearLayout>(R.id.Saturday1)
        saturdaybutton.setOnClickListener {
            val intent = Intent(this, SaturdayActivity::class.java)
            startActivity(intent)
        }
        val addroutinebutton = findViewById<AppCompatButton>(R.id.addroutine)
        addroutinebutton.setOnClickListener {
            val intent = Intent(this, RoutineActivity::class.java)
            intent.putExtra("day", Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_WEEK))
            startActivity(intent)
        }
        val pengaturanButton = findViewById<AppCompatButton>(R.id.pengaturan)
        pengaturanButton.setOnClickListener {
            val intent = Intent(this, PengaturActivity::class.java)
            startActivity(intent)
        }

        val currentDate = Calendar.getInstance(TimeZone.getDefault())
        val hariini = currentDate.get(Calendar.DAY_OF_WEEK)
        val component = arrayOf(sundaybutton, sundaybutton, mondaybutton, tuesdaybutton, wednesdaybutton, thursdaybutton, fridaybutton, saturdaybutton)
        component[hariini].setBackgroundResource(R.drawable.biru_select)
    }

    val db by lazy {AlarmDB(this)}
    lateinit var alarmAdapter: AlarmAdapter

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {

            var alarms = db.alarmDAO().getSundayAlarm() //ngasih nilai default, biar gak error
            val hariini = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_WEEK) //dapetin value hari

            //menyesuaikan output dari value hari
            if ( hariini == Calendar.SUNDAY) {
                alarms = db.alarmDAO().getSundayAlarm()
            }
            else if (hariini == Calendar.MONDAY) {
                alarms = db.alarmDAO().getMondayAlarm()
            }
            else if (hariini == Calendar.TUESDAY) {
                alarms = db.alarmDAO().getTuesdayAlarm()
            }
            else if (hariini == Calendar.WEDNESDAY) {
                alarms = db.alarmDAO().getWednesdayAlarm()
            }
            else if (hariini == Calendar.THURSDAY) {
                alarms = db.alarmDAO().getThursdayAlarm()
            }
            else if (hariini == Calendar.FRIDAY) {
                alarms = db.alarmDAO().getFridayAlarm()
            }
            else if (hariini == Calendar.SATURDAY) {
                alarms = db.alarmDAO().getSaturdayAlarm()
            }

            withContext(Dispatchers.Main){
                alarmAdapter.setData(alarms)
            }
        }
    }

    private fun setupRecycler(){
        alarmAdapter = AlarmAdapter(arrayListOf()){
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }
        today_viewer.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = alarmAdapter
        }
    }
}
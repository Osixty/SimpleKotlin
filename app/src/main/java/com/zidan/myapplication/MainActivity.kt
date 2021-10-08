package com.zidan.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import java.util.Calendar
import java.util.TimeZone;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
        val addroutinebutton = findViewById<Button>(R.id.addroutine)
        addroutinebutton.setOnClickListener {
            val intent = Intent(this, RoutineActivity::class.java)
            startActivity(intent)
        }

        val currentDate = Calendar.getInstance(TimeZone.getDefault())
        val hariini = currentDate.get(Calendar.DAY_OF_WEEK)
        val component = arrayOf(sundaybutton, sundaybutton, mondaybutton, tuesdaybutton, wednesdaybutton, thursdaybutton, fridaybutton, saturdaybutton)
        component[hariini].setBackgroundResource(R.drawable.biru_select)

    }
}
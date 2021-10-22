package com.zidan.myapplication

import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity


class PengaturActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengaturan)

        val sound_toggle = findViewById<ToggleButton>(R.id.alarmsound)
        val vibration_toggle = findViewById<ToggleButton>(R.id.vibration)
        val notification_toggle = findViewById<ToggleButton>(R.id.Notif)

        var sound = true
        var vibration = true
        var notification = true

        sound_toggle.setOnCheckedChangeListener {_, isChecked ->
            //antara true / false
            sound = isChecked
        }
        vibration_toggle.setOnCheckedChangeListener {_, isChecked ->
            vibration = isChecked
        }
        notification_toggle.setOnCheckedChangeListener {_, isChecked ->
            notification = isChecked
        }
    }
}









package com.zidan.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zidan.myapplication.room.Alarm
import kotlinx.android.synthetic.main.activity_routin.view.*
import kotlinx.android.synthetic.main.item_alarm.view.*

class AlarmAdapter(private val alarms: ArrayList<Alarm>, val listener: (Alarm) -> Unit) : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarm  = alarms[position]
        val waktu = "${if (alarm.jam < 10) "0${alarm.jam}" else alarm.jam} : ${if (alarm.menit < 10) "0${alarm.menit}" else alarm.menit}"
        holder.view.text_waktu.text = waktu
        holder.view.text_j_alarm.text = alarm.judul_alarm
        holder.view.text_d_alarm.text = alarm.detail_alarm

        holder.itemView.setOnClickListener{
            listener(alarm)
        }
    }

    override fun getItemCount() = alarms.size

    class AlarmViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Alarm>){
        alarms.clear()
        alarms.addAll(list)
        notifyDataSetChanged()
    }
}
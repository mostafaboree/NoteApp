package com.example.myapplication.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.example.myapplication.domin.model.Note


class NoteAdapter(val context: Context,val list: List<Note>): RecyclerView.Adapter<NoteHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val layoutInflater=LayoutInflater.from(context).inflate(R.layout.note_item,null,false)
        return NoteHolder(layoutInflater.rootView)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val l=list
       val item= l[position]
        holder.title.text=item.title
        holder.bady.text=item.body
    }
}
class NoteHolder(itemView: View) : ViewHolder(itemView) {
    val v=itemView
    var title=v.findViewById<TextView>(R.id.tv_title)
    var bady=v.findViewById<TextView>(R.id.tv_body)

}
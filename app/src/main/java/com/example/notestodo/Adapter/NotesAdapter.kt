package com.example.notestodo.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notestodo.Models.Note
import com.example.notestodo.R
import kotlin.random.Random

class NotesAdapter(private var context: Context,var listener:NoteClickedListener): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val noteList=ArrayList<Note>()
    private val fullList=ArrayList<Note>()

    inner class NoteViewHolder(itemview : View): RecyclerView.ViewHolder(itemview){
        val notes_layout=itemview.findViewById<CardView>(R.id.card_layout)
        val title=itemview.findViewById<TextView>(R.id.tv_title)
        val note=itemview.findViewById<TextView>(R.id.tv_note)
        val date=itemview.findViewById<TextView>(R.id.tv_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        )
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentnNote=noteList[position]
        holder.title.text=currentnNote.title
        holder.title.isSelected = true

        holder.note.text=currentnNote.note

        holder.date.text=currentnNote.date
        holder.date.isSelected = true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(RandomColor(),null))

        holder.notes_layout.setOnClickListener{
            listener.onClicked(noteList[holder.adapterPosition])
        }

        holder.notes_layout.setOnClickListener{
            listener.onLongClicked(noteList[holder.adapterPosition],holder.notes_layout)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun updateList(newlist: List<Note>){
        fullList.clear()
        fullList.addAll(newlist)

        noteList.clear()
        noteList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search: String){

        noteList.clear()

        for (item in fullList){

            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                    item.note?.lowercase()?.contains(search.lowercase())==true){

                noteList.add(item)
            }

        }
        notifyDataSetChanged()
    }

    fun RandomColor() : Int{

        val list = ArrayList<Int>()
        list.add(R.color.notescolor1)
        list.add(R.color.notescolor2)
        list.add(R.color.notescolor3)
        list.add(R.color.notescolor4)
        list.add(R.color.notescolor5)
        list.add(R.color.notescolor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]

    }

    interface NoteClickedListener{

        fun onClicked(note:Note)
        fun onLongClicked(note:Note,cardView: CardView)
    }

}
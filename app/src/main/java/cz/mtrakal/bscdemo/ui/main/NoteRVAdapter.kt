package cz.mtrakal.bscdemo.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.mtrakal.bscdemo.R
import cz.mtrakal.bscdemo.model.Note

import kotlinx.android.synthetic.main.item_note.view.vNote

class NoteRVAdapter(private val context: Context) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    var items: List<Note> = emptyList()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vNote.text = items[position].title
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vNote: TextView = view.vNote
    }
}


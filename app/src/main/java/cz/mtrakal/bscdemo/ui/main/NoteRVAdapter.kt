package cz.mtrakal.bscdemo.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import cz.mtrakal.bscdemo.R
import cz.mtrakal.bscdemo.model.Note
import cz.mtrakal.bscdemo.network.NetworkService
import kotlinx.android.synthetic.main.item_note.view.*

class NoteRVAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    var items: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.note = items[position]
        holder.vNote.text = holder.note.title

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var note: Note
        val vNote: TextView = view.vNote
        val vDelete: ImageView = view.vDelete

        init {
            vNote.setOnClickListener {
                MaterialDialog(view.context).show {
                    input(prefill = note.title) { _, charSequence ->
                        val newNote = Note(note.id, charSequence.toString())
                        NetworkService.updateNote(newNote) { response ->
                            // todo update [MainFragment.model]
                        }
                    }
                    positiveButton(android.R.string.ok)
                    negativeButton(android.R.string.cancel)
                }
            }

            vDelete.setOnClickListener {
                NetworkService.deleteNote(note.id) { response ->
                    // todo update [MainFragment.model]
                }
            }
        }
    }
}


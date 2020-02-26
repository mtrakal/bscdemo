package cz.mtrakal.bscdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import cz.mtrakal.bscdemo.R
import cz.mtrakal.bscdemo.model.Note
import cz.mtrakal.bscdemo.network.NetworkService
import cz.mtrakal.bscdemo.network.ResponseData
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: NoteRVAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    val model: NoteViewModel by activityViewModels()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.getNoteList().observe(viewLifecycleOwner, Observer<List<Note>> { notes ->
            viewAdapter.items = notes
        })
        initRecyclerView()

        view.vNoteAdd.setOnClickListener {
            MaterialDialog(requireContext()).show {
                input() { _, charSequence ->
                    NetworkService.putNote(charSequence.toString()) { response ->
                        when (response) {
                            is ResponseData.Success -> model.updateData()
                            is ResponseData.Failure<*> -> Toast.makeText(
                                requireContext(),
                                "Request failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                positiveButton(android.R.string.ok)
                negativeButton(android.R.string.cancel)
            }
        }
    }

    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        vRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            viewAdapter = NoteRVAdapter(requireContext())
            adapter = viewAdapter
        }
    }
}

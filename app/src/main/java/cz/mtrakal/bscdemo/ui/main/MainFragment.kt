package cz.mtrakal.bscdemo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.mtrakal.bscdemo.R
import cz.mtrakal.bscdemo.model.Note
import kotlinx.android.synthetic.main.main_fragment.*

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
        model.getNoteList().observe(viewLifecycleOwner, Observer<List<Note>>{ notes ->
            viewAdapter.items = notes
        })
        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewManager = LinearLayoutManager(requireContext())

        vRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            viewAdapter = NoteRVAdapter(requireContext())
            adapter = viewAdapter
        }
    }
}

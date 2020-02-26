package cz.mtrakal.bscdemo.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cz.mtrakal.bscdemo.model.Note
import cz.mtrakal.bscdemo.network.NetworkService
import cz.mtrakal.bscdemo.network.ResponseData

class NoteViewModel : ViewModel() {
    private val _noteList: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>().also {
            updateData()
        }
    }

    fun getNoteList() = _noteList

    fun updateData() {
        NetworkService.getNotes { response ->
            run {
                when (response) {
                    is ResponseData.Success -> _noteList.postValue(response.data)
                    is ResponseData.Failure<*> -> Log.w("XXX", "Failed load data!")
                    else -> TODO("not implemented")
                }
            }
        }
    }
}

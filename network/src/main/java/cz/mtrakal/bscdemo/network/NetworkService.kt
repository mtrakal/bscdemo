package cz.mtrakal.bscdemo.network

import cz.mtrakal.bscdemo.model.Note
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    val retrofit = Retrofit.Builder()
        .client(OkHttpClient())
        .baseUrl(NotesApiService.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    public val notesApiService = retrofit.create(NotesApiService::class.java)

    fun getNotes(onResult: (response: ResponseData<List<Note>>) -> Unit) {
        notesApiService.getNotes().transform(onResult)
    }

    fun getNote(id: Int, onResult: (response: ResponseData<Note>) -> Unit) {
        notesApiService.getNote(id).transform(onResult)
    }

    fun putNote(title: String, onResult: (response: ResponseData<Note>) -> Unit) {
        notesApiService.putNote(title).transform(onResult)
    }

    fun updateNote(note: Note, onResult: (response: ResponseData<Note>) -> Unit) {
        notesApiService.updateNote(note.id, note.title).transform(onResult)
    }

    fun deleteNote(id: Int, onResult: (response: ResponseData<Void?>) -> Unit) {
        notesApiService.deleteNote(id).transform(onResult)
    }
}

fun <T> Call<T>.transform(onResult: (response: ResponseData<T>) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
            onResult(ResponseData.process { response })
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            onResult(ResponseData.error(throwable))
        }
    })
}
package cz.mtrakal.bscdemo.network

import cz.mtrakal.bscdemo.model.Note
import retrofit2.Call
import retrofit2.http.*

interface NotesApiService {
    companion object {
        const val API_BASE_URL = "https://private-anon-9022d4b885-note10.apiary-mock.com/"
    }

    @Headers("Content-Type: application/json")
    @GET("notes")
    fun getNotes(): Call<List<Note>>

    @Headers("Content-Type: application/json")
    @GET("notes/{id}")
    fun getNote(@Path("id") id: Int): Call<Note>

    @Headers("Content-Type: application/json")
    @POST("notes")
    fun putNote(@Body title: String): Call<Note>

    @Headers("Content-Type: application/json")
    @PUT("notes/{id}")
    fun updateNote(@Path("id") id: Int, @Body title: String): Call<Note>

    @Headers("Content-Type: application/json")
    @DELETE("notes/{id}")
    fun deleteNote(@Path("id") id: Int): Call<Void?>
}
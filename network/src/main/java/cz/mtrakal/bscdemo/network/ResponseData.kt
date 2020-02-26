package cz.mtrakal.bscdemo.network

import android.util.Log
import retrofit2.Response

sealed class ResponseData<T> {
    class Success<T>(response: Response<T>) : ResponseData<T>() {
        val data: T? = response.body()
    }

    sealed class Failure<T> {
        class Error<T>(response: Response<T>) : ResponseData<T>() {
            val error: String = "Error: ${response.code()}"
        }

        class Exception<T>(exception: Throwable) : ResponseData<T>() {
            val error: String = "Error: ${exception.message}"
        }
    }

    companion object {
        fun <T> error(ex: Throwable) = Failure.Exception<T>(ex)
        fun <T> process(f: () -> Response<T>): ResponseData<T> = try {
            val response = f()
            Log.i("Response", "Is success: ${response.isSuccessful}, code: ${response.code()}")
            if (response.isSuccessful) {
                Success(response)
            } else {
                Failure.Error(response)
            }
        } catch (ex: Exception) {
            Failure.Exception(ex)
        }
    }
}
package cz.mtrakal.bscdemo.network

import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class ResponseDataTest {

    @Before
    fun setUp() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }

    @Test
    fun testSuccessResponse() {
        val x = Response.success(TestData())
        val responseData = ResponseData.process { x }
        Assert.assertTrue(responseData is ResponseData.Success)
    }

    @Test
    fun testFailResponse() {
        val rBody = "test".toResponseBody("text/plain".toMediaType())
        val x = Response.error<TestData>(501, rBody)
        val responseData = ResponseData.process { x }
        Assert.assertTrue(responseData is ResponseData.Failure.Error)
    }

    data class TestData(val data: String = "test")
}
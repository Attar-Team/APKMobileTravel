package com.example.rahmatantravel.api

import android.os.Handler
import android.os.Looper
import com.example.rahmatantravel.MainActivity
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream

class UploadRequestBody(
    private val file: File,
    private val contentType: String,
    private val callback: UploadCallback
) : RequestBody() {

    interface UploadCallback {
        fun onProgressUpdate(percentage: Int)
    }

    override fun contentType(): MediaType? {
        return MediaType.parse("$contentType/*")
    }

    override fun contentLength(): Long {
        return file.length()
    }

    override fun writeTo(sink: BufferedSink) {
        val length = file.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fileInputStream = FileInputStream(file)
        var uploaded: Long = 0

        fileInputStream.use { inputStream ->
            var read: Int
            val handler = Handler(Looper.getMainLooper())

            while (inputStream.read(buffer).also { read = it } != -1) {
                uploaded += read.toLong()
                sink.write(buffer, 0, read)

                // Update progress on the main thread
                handler.post { callback.onProgressUpdate((100 * uploaded / length).toInt()) }
            }
        }
    }

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 2048
    }
}
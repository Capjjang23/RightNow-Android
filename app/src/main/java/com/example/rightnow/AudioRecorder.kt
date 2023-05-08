package com.example.rightnow
import android.content.Context
import android.media.MediaRecorder
import android.util.Log
import com.example.rightnow.apiManager.RecordApiManager
import com.example.rightnow.model.RecordModel
import java.io.IOException

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream

class AudioRecorder {
    private var mediaRecorder: MediaRecorder? = null
    private var isRecording = false

    fun startRecording(outputFile: String) {
        if (isRecording) return

        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(outputFile)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)


            try {
                prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            start()
            isRecording = true
        }
    }

    fun stopRecording(byteArray : ByteArray, context : Context ) {
        if (!isRecording) return

        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        isRecording = false

        // 서버 전송
        val recordModel = RecordModel(byteArray)
        val apiManager = RecordApiManager.getInstance(context)
        apiManager?.postTest(recordModel)
    }

    fun mediaRecorderToByteArray(outputFile: String): ByteArray? {
        val file = File(outputFile)
        if (!file.exists()) {
            return null
        }

        val inputStream = FileInputStream(file)
        val buffer = ByteArrayOutputStream()

        inputStream.use { input ->
            buffer.use { output ->
                val data = ByteArray(1024)
                var count: Int
                while (input.read(data).also { count = it } != -1) {
                    output.write(data, 0, count)
                }
                output.flush()
            }
        }

        return buffer.toByteArray()
    }

}

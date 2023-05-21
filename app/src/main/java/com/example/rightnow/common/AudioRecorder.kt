package com.example.rightnow.common
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
     var isRecording = false
    private var fileName = ""

    fun startRecording(outputFile: String, apiManager: RecordApiManager) {
        if (isRecording) return

        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS) // or MediaRecorder.OutputFormat.MPEG_4
            setOutputFile(outputFile)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC) // or MediaRecorder.AudioEncoder.DEFAULT
            setAudioSamplingRate(44100) // set the desired sampling rate
            setAudioEncodingBitRate(320000)
            setMaxDuration(2000)

            try {
                prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            start()
            isRecording = true
        }

        Thread.sleep(2000)

        // 서버 전송
        isRecording = false
        Log.d("[mmihye]","녹음 멈춤 & 서버 전송")
        val byteArray = mediaRecorderToByteArray(outputFile)
        val recordModel = byteArray?.let { RecordModel(it) }
        if (recordModel != null) {
            apiManager.postTest(recordModel)
        }

    }

    fun restartRecording(context: Context){
        if (!isRecording) return

        isRecording = false
        mediaRecorder?.apply {
            release()
        }

        // 서버 전송
        Log.d("send file",fileName)
        val byteArray = mediaRecorderToByteArray(fileName)
        val recordModel = byteArray?.let { RecordModel(it) }
        val apiManager = RecordApiManager.getInstance(context)
        if (recordModel != null) {
            apiManager?.postTest(recordModel)
        }

        mediaRecorder = null
    }

    fun stopRecording(context : Context ) {
        if (!isRecording) return

        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        isRecording = false


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

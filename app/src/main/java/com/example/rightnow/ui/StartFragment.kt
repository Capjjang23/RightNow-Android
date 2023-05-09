package com.example.rightnow.ui

import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.example.travalue.base.BaseFragment
import com.example.rightnow.AudioRecorder
import com.example.rightnow.R
import com.example.rightnow.apiManager.RecordApiManager
import com.example.rightnow.databinding.FragmentStartBinding
import com.example.rightnow.model.RecordModel
import com.example.rightnow.model.byteArrayToRecordModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.FileOutputStream
import java.util.Date

class StartFragment : BaseFragment<FragmentStartBinding>(R.layout.fragment_start) {

    private val REQUEST_RECORD_AUDIO_PERMISSION = 100
    val audioRecorder = AudioRecorder()
    var byteArray = ByteArray(1000)

//    val audioSource = MediaRecorder.AudioSource.MIC
//    val sampleRate = 44100
//    val channelConfig = AudioFormat.CHANNEL_IN_MONO
//    val audioFormat = AudioFormat.ENCODING_PCM_16BIT
//    val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)
//
//    lateinit var audioRecord : AudioRecord
//    lateinit var audioData : ByteArray
//    var readBytes = 10



    override fun initStartView() {
        super.initStartView()

        //녹음
//        recordAudio()

        // 녹음 bytArray
        val filename: String = Date().time.toString()+".aac"
        val filePath = Environment.getExternalStorageDirectory().absolutePath+"/Download/"+filename
        audioRecorder.startRecording(filePath)
//        byteArray = audioRecorder.mediaRecorderToByteArray(filePath)!!
        Log.d("filePathh", filePath)
    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnStart.setOnClickListener {
            context?.let { it1 -> audioRecorder.stopRecording(it1) }

            navController.navigate(R.id.action_startFragment_to_quizFragment)


        }

    }


    private fun saveAudioDataToFile(data: ByteArray) {
        // 저장할 파일 경로 지정
        val filename: String = Date().time.toString()+".aac"
        val filePath = Environment.getExternalStorageDirectory().absolutePath+"/Download/"+filename
        Log.d("filePathh",filePath.toString())

        // FileOutputStream을 사용하여 파일에 데이터를 저장
        FileOutputStream(filePath).use { outStream ->
            outStream.write(data)
        }

        playAudioFile(filePath)
    }

    private fun playAudioFile(filePath: String) {
        val mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(filePath)
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }
        mediaPlayer.prepareAsync()
    }

}
package com.example.rightnow

import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.example.travalue.base.BaseFragment
import com.example.rightnow.databinding.FragmentStartBinding
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.File
import java.io.FileOutputStream
import java.util.Date

class StartFragment : BaseFragment<FragmentStartBinding>(R.layout.fragment_start) {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://223.194.156.113:8000/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()


    private val REQUEST_RECORD_AUDIO_PERMISSION = 100

    val audioSource = MediaRecorder.AudioSource.MIC
    val sampleRate = 44100
    val channelConfig = AudioFormat.CHANNEL_IN_MONO
    val audioFormat = AudioFormat.ENCODING_PCM_16BIT
    val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)

    lateinit var audioRecord : AudioRecord
    lateinit var audioData : ByteArray
    var readBytes = 10

    private fun recordAudio(): ByteArray {
        // 오디오 포맷 설정
        val audioSource = MediaRecorder.AudioSource.MIC
        val sampleRate = 44100
        val channelConfig = AudioFormat.CHANNEL_IN_MONO
        val audioFormat = AudioFormat.ENCODING_PCM_16BIT
        val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)
        if (context?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.RECORD_AUDIO) }
            == PackageManager.PERMISSION_GRANTED) {
            // 권한이 이미 부여되어 있습니다.
            // 여기서 API를 호출합니다.
        } else {
            // 권한이 부여되어 있지 않습니다. 권한 요청 대화상자를 표시하여 권한을 요청합니다.
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(android.Manifest.permission.RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO_PERMISSION
                )
            }
        }
        val audioRecord = AudioRecord(audioSource, sampleRate, channelConfig, audioFormat, bufferSize)

        // 녹음 시작
        val audioData = ByteArray(bufferSize)
        audioRecord.startRecording()
        val readBytes = audioRecord.read(audioData, 0, bufferSize)

        // 녹음 종료
        audioRecord.stop()
        audioRecord.release()


        saveAudioDataToFile(audioData)

        // 녹음된 데이터 반환
        return audioData.copyOfRange(0, readBytes)
    }

    override fun initStartView() {
        super.initStartView()

        //녹음
        recordAudio()

    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.btnStart.setOnClickListener {

//            val byteArray = "Hello".toByteArray()




//            val file = File(context?.cacheDir, "audio.mp3")
//            file.writeBytes(audioData.copyOfRange(0, readBytes))
//
//            val mediaPlayer = MediaPlayer()
//            mediaPlayer.setDataSource(file.path)
//
//            mediaPlayer.prepare()
//            mediaPlayer.start()
//
//            mediaPlayer.release()


            navController.navigate(R.id.action_startFragment_to_quizFragment)

//            val apiService = retrofit.create(ApiService::class.java)
//            val requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), audioData.copyOfRange(0, readBytes))
//            val call = apiService.postByteArray(requestBody)
//            call.enqueue(object : Callback<String> {
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    // Handle the response from the server here.
//                }
//
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    // Handle errors that occur during the request here.
//                }
//            })

        }

    }

    interface ApiService {
        @POST("process_audio")
        fun postByteArray(@Body byteArray: RequestBody): Call<String>
    }


    private fun saveAudioDataToFile(data: ByteArray) {
        // 저장할 파일 경로 지정
        val filename: String = Date().time.toString()+".mp3"
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
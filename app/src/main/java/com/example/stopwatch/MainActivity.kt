package com.example.stopwatch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.stopwatch.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timer

// 네트워크 작업, 데이터베이스 작업, 계산량이 많은 작업은 백그라운드 스레드에서 진행하는 것이 좋다.

class MainActivity : AppCompatActivity() , View.OnClickListener {

    var isRunning = false
    var timer : Timer?= null
    var time = 0
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnStart.setOnClickListener(this)
        binding.btnRefresh.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_start -> if (isRunning) pause() else start()
            R.id.btn_refresh -> refresh()
        }
    }

    private fun start() {
        // 측정시작
        binding.btnStart.text = "일시정지"
        binding.btnStart.setBackgroundColor(getColor(R.color.red))
        isRunning = true

        timer = timer(period = 10) {
            time++ // 10밀리초 단위
            val milli_second = time % 100
            val second = (time % 6000) / 100
            val minute = time / 6000

            runOnUiThread {
                if (isRunning) {
                    binding.tvMillisecond.text = if (milli_second < 10) ".0$milli_second" else ".$milli_second" // 초
                    binding.tvSecond.text = if (second < 10) ":0$second" else ":$second"
                    binding.tvMinute.text = "$minute"
                }
            }
        }
    }

    private fun pause() {
        // 측정 정지
        binding.btnStart.text = "시작"
        binding.btnStart.setBackgroundColor(getColor(R.color.blue))
        isRunning = false
        timer?.cancel()
    }

    @SuppressLint("SetTextI18n")
    private fun refresh() {
        // 초기화
        timer?.cancel()
        binding.btnStart.text = "시작"
        binding.btnStart.setBackgroundColor(getColor(R.color.blue))
        isRunning = false

        // 타이머 초기화
        time = 0
        binding.tvMillisecond.text = ".00"
        binding.tvSecond.text = ":00"
        binding.tvMinute.text = "00"
    }
}
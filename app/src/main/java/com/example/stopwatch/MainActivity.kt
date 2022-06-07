package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , View.OnClickListener {

    var isRunning = false
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
    }

    private fun pause() {
        // 측정 정지
    }

    private fun refresh() {
        // 초기화
    }
}
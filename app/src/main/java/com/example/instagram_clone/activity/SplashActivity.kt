package com.example.instagram_clone.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import com.example.instagram_clone.R

class SplashActivity:BaseActivity() {
    val TAG = SplashActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initView()
    }

    private fun initView() {
        countDownTimer()
    }

    private fun countDownTimer() {
        object : CountDownTimer(2000,1000){
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                callSignActivity()
            }
        }.start()
    }

    private fun callSignActivity(){
        val intent = Intent(this,SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}
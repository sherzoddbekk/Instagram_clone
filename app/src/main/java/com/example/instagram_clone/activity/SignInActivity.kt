package com.example.instagram_clone.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.instagram_clone.MainActivity
import com.example.instagram_clone.R

/*
in signUpActivity ,user can login using email, password
 */

class SignInActivity : BaseActivity() {
    val TAG = SplashActivity::class.java.toString()
    lateinit var et_email: EditText
    lateinit var et_password: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        initView()
    }

    private fun initView() {
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)

        val b_signin = findViewById<Button>(R.id.b_signin)
        b_signin.setOnClickListener {
            callMainActivity()
        }
        val tv_signUp = findViewById<TextView>(R.id.tv_signup)
        tv_signUp.setOnClickListener {
            callSignUpActivity()
        }
    }

    private fun callSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun callMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
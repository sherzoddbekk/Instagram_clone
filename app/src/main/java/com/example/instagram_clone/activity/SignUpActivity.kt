package com.example.instagram_clone.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.instagram_clone.R

/**
In SignUpActivity user can signup using fullname email password
 */
class SignUpActivity : BaseActivity() {

    val TAG = SplashActivity::class.java.toString()
    lateinit var et_fullname:EditText
    lateinit var et_password:EditText
    lateinit var et_email:EditText
    lateinit var et_cpassword:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initView()
    }

    private fun initView() {
        et_fullname = findViewById(R.id.et_fullname)
        et_email = findViewById(R.id.et_email)
        et_password =findViewById(R.id.et_password)
        et_cpassword = findViewById(R.id.et_confirm_password)
        val b_signup = findViewById<Button>(R.id.b_signup)
        b_signup.setOnClickListener {
            finish()
        }
        val tv_signin = findViewById<TextView>(R.id.tv_signin)
        tv_signin.setOnClickListener {
            finish()
        }
    }
}
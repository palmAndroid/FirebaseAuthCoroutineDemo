package com.loginsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_Login.setOnClickListener(this)
        btn_create.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
     when(v?.id){
      R.id.btn_Login -> startActivity(Intent(this, LoginActivity::class.java))
      R.id.btn_create -> startActivity(Intent(this, CreateAccountActivity::class.java))
     }
    }
}
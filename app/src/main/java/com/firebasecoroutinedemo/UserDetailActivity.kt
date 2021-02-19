package com.firebasecoroutinedemo

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebasecoroutinedemo.constants.ApplicationConstants
import com.firebasecoroutinedemo.preference.PreferenceStore
import kotlinx.android.synthetic.main.user_detail.*

class UserDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail)
        text_user.text = "Welcome  ${PreferenceStore(this as Context).getValue(ApplicationConstants.USERNAME,"")}"
    }
}
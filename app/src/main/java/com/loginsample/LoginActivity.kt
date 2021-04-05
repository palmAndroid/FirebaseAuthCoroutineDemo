package com.loginsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.loginsample.constants.ApplicationConstants
import com.loginsample.entity.UserRequest
import com.loginsample.preference.PreferenceStore
import com.loginsample.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var viewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        btn_login.setOnClickListener(this)
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button -> loginAccount(
                editTextEmailAddress.text.toString(),
                editTextPassword.text.toString()
            )
        }
    }

    private fun loginAccount(email: String, password: String) {
        if(email.isNullOrEmpty() &&  password.isNullOrEmpty()){
            showToast("Please fill all the fields!")
        }else if (!isValidEmail(email)){
            showToast("Email id is not valid!")
        }else{
            progressBar.visibility = View.VISIBLE
            viewModel.proceedWithLogin(this, UserRequest( email, password))
        }
        viewModel.userList.observe(this, Observer { it->
            if (it.isError){
                progressBar.visibility = View.GONE
                showToast("Something went wrong , Please try again later!")
            }else{
                progressBar.visibility = View.GONE
                var preferences = PreferenceStore(this as Context)
                preferences.saveValue(ApplicationConstants.UID,it.uid)
                preferences.saveValue(ApplicationConstants.USERNAME,it.name)
                preferences.saveValue(ApplicationConstants.EMAIL,it.email)
                showToast("Your account has been created successfully!")
                startActivity(Intent(this, MainActivity::class.java))
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
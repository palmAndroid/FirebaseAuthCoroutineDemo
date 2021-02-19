package com.firebasecoroutinedemo

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
import com.firebasecoroutinedemo.constants.ApplicationConstants
import com.firebasecoroutinedemo.entity.UserRequest
import com.firebasecoroutinedemo.preference.PreferenceStore
import com.firebasecoroutinedemo.viewmodel.CreateAccountViewModel
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.create_account.*


class CreateAccountActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var viewModel : CreateAccountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_account)
        FirebaseApp.initializeApp(this)
        viewModel = ViewModelProvider(this).get(CreateAccountViewModel::class.java)
        button.setOnClickListener(this)
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button -> createAccount(
                editEmailAddress.text.toString(),
                editPassword.text.toString(),
                editPassword2.text.toString()
            )
        }
    }

    private fun createAccount(email: String, password: String, confirm_password: String) {
        if(email.isNullOrEmpty() &&  password.isNullOrEmpty() && confirm_password.isNullOrEmpty()){
          showToast("Please fill all the fields!")
        }else if (!isValidEmail(email)){
            showToast("Email id is not valid!")
        }else if (password != confirm_password){
            showToast("Password doesn't match with confirm password!")
        }else{
            viewModel.proceedWithAuthentication(this, UserRequest( email, password))
        }
        viewModel.userList.observe(this, Observer { it->
            if (it.isError){
             showToast("Something went wrong , Please try again later!")
        }else{
           /* var bundle = bundleOf("email" to it.email ,
                "name" to it.name,
                "uid" to it.uid)*/
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
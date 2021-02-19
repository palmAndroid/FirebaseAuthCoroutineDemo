package com.firebasecoroutinedemo.viewmodel

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.firebasecoroutinedemo.entity.User
import com.firebasecoroutinedemo.entity.UserRequest
import com.firebasecoroutinedemo.service.FirebaseAuthService

class LoginViewModel : ViewModel() {
    var userList = MutableLiveData<User>()

    fun proceedWithLogin(activity: LifecycleOwner, userReq: UserRequest){
        FirebaseAuthService(activity as Activity).loginFirebaseUser(userReq).observe(activity,
            Observer {authenticatedUser ->
                if (!authenticatedUser.displayName.isNullOrEmpty()) {
                    var uid : String? = authenticatedUser.uid
                    var email : String? = authenticatedUser.email
                    var name : String?  = authenticatedUser.displayName
                    userList.value = User(uid, email, name, false)
                } else {
                    userList.value = User("", "", "", true)
                }
            })

    }
}
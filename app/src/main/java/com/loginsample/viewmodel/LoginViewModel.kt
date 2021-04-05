package com.loginsample.viewmodel

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.loginsample.entity.User
import com.loginsample.entity.UserRequest
import com.loginsample.service.FirebaseAuthService

class LoginViewModel : ViewModel() {
    var userList = MutableLiveData<User>()

    fun proceedWithLogin(activity: LifecycleOwner, userReq: UserRequest){
        FirebaseAuthService(activity as Activity).loginFirebaseUser(userReq).observe(activity,
            Observer {authenticatedUser ->
                if (!authenticatedUser.isError) {
                    var uid : String? = authenticatedUser.firebaseUser?.uid
                    var email : String? = authenticatedUser.firebaseUser?.email
                    var name : String?  = authenticatedUser.firebaseUser?.displayName
                    userList.value = User(uid, email, name, false)
                } else {
                    userList.value = User("", "",
                        "", true,authenticatedUser.errorMessages)
                }
            })

    }
}
package com.loginsample.service

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.loginsample.entity.UserRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.loginsample.entity.UserResponse

class FirebaseAuthService(activity: Activity) {

    var firstbaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var activity: Activity = activity
    var userData = MutableLiveData<UserResponse>()
    var loginUserData = MutableLiveData<UserResponse>()

     fun createFirebaseUser(userRequest: UserRequest): MutableLiveData<UserResponse> {
        firstbaseAuth.createUserWithEmailAndPassword(userRequest.email, userRequest.password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    userData.value = UserResponse(firstbaseAuth.currentUser,
                        false,"")
                } else {
                    userData.value = UserResponse(null,
                        true,task.exception?.message.toString())
                }
            }
        return userData
    }

    fun loginFirebaseUser(userRequest: UserRequest): MutableLiveData<UserResponse> {
        firstbaseAuth.signInWithEmailAndPassword(userRequest.email, userRequest.password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    loginUserData.value = UserResponse(firstbaseAuth.currentUser,
                        false,"")
                } else {
                    loginUserData.value = UserResponse(null,
                        true,task.exception?.message.toString())
                }
            }
        return loginUserData
    }
}
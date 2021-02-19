package com.firebasecoroutinedemo.service

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.firebasecoroutinedemo.entity.UserRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthService(activity: Activity) {

    var firstbaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var activity: Activity = activity
    var userData = MutableLiveData<FirebaseUser>()

     fun createFirebaseUser(userRequest: UserRequest): MutableLiveData<FirebaseUser> {
        firstbaseAuth.createUserWithEmailAndPassword(userRequest.email, userRequest.password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    userData.value = firstbaseAuth.currentUser
                } else {
                    userData.value = null
                }
            }
        return userData
    }

    fun loginFirebaseUser(userRequest: UserRequest): MutableLiveData<FirebaseUser> {
        firstbaseAuth.signInWithEmailAndPassword(userRequest.email, userRequest.password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    userData.value = firstbaseAuth.currentUser
                } else {
                    userData.value = null
                }
            }
        return userData
    }
}
package com.loginsample.viewmodel

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.loginsample.entity.User
import com.loginsample.entity.UserRequest
import com.loginsample.service.FirebaseAuthService

class CreateAccountViewModel : ViewModel() {
    var userList = MutableLiveData<User>()
   fun proceedWithAuthentication(activity: LifecycleOwner, userReq: UserRequest){
     FirebaseAuthService(activity as Activity).createFirebaseUser(userReq).observe(activity,
         Observer {userResponse ->
             if (!userResponse.isError) {
                 var uid : String? = userResponse.firebaseUser?.uid
                 var email : String? = userResponse.firebaseUser?.email
                 var name : String?  = userResponse.firebaseUser?.displayName
                 userList.value = User(uid, email, name, false)
         } else {
                 userList.value = User("", "",
                     "", true,userResponse.errorMessages)
         }
         })

   }
}
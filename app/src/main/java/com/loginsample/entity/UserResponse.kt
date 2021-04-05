package com.loginsample.entity

import com.google.firebase.auth.FirebaseUser

data class UserResponse (val firebaseUser: FirebaseUser?,
                          val isError : Boolean,
                         val errorMessages: String
)
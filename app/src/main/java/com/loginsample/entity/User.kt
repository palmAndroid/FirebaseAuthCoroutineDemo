package com.loginsample.entity

data class User(val uid : String?,
                val email : String?, val name : String?, val isError : Boolean,
                val errorMessage : String? = ""
)

package com.waldorf.comparator.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginService {

    private lateinit var auth: FirebaseAuth
    // Initialize Firebase Auth

    fun authCreate(){
        auth = Firebase.auth
    }

    fun authStart(): Boolean{
        val currentUser = auth.currentUser
        return currentUser == null
    }
}
package com.example.twitter.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twitter.model.TweetModel
import com.example.twitter.model.UserModel
import com.example.twitter.utils.SharedPref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class AddTweetViewModel : ViewModel() {
    private val db = FirebaseDatabase.getInstance()
    val userRef = db.getReference("tweets")

    private val _isPosted = MutableLiveData<Boolean>(false)
    val isPosted: MutableLiveData<Boolean> = _isPosted
    fun saveData(
        tweet:String,
        userID:String
    ) {
        val tweetData = TweetModel(tweet,userID,System.currentTimeMillis().toString())
        userRef.child(userRef.push().key!!).setValue(tweetData).addOnSuccessListener {
            _isPosted.postValue(true)
        }.addOnFailureListener {
            _isPosted.postValue(false)
        }
    }


}
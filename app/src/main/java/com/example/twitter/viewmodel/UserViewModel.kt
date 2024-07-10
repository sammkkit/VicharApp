package com.example.twitter.viewmodel

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.snap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.twitter.model.TweetModel
import com.example.twitter.model.UserModel
import com.example.twitter.utils.SharedPref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class UserViewModel : ViewModel()  {
    private val db = FirebaseDatabase.getInstance()
    val tweetRef = db.getReference("tweets")

    private val _tweets = MutableLiveData<List<TweetModel>>()
    val tweets: LiveData<List<TweetModel>> get() = _tweets

    init {
        fetchTweets()
    }

    fun fetchTweets(){
            tweetRef.orderByChild("userID").equalTo(FirebaseAuth.getInstance().currentUser!!.uid).addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tweetList = snapshot.children.mapNotNull {
                        it.getValue(TweetModel::class.java)
                    }
                        _tweets.postValue(tweetList)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }


}
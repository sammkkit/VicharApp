package com.example.twitter.viewmodel

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.snap
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
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class HomeViewModel : ViewModel()  {
    private val db = FirebaseDatabase.getInstance()
    val tweet = db.getReference("tweets")

//    val authViewModel:AuthViewModel= viewModel()

    private var _tweetAndUsers = MutableLiveData<List<Pair<TweetModel,UserModel>>>()
    val tweetAndUsers: MutableLiveData<List<Pair<TweetModel,UserModel>>> = _tweetAndUsers
//
//    private var _tweetByuser = MutableLiveData<List<TweetModel>>()
//    val tweetByuser: MutableLiveData<List<TweetModel>> = _tweetByuser
    init {
        fetchtweetsAndusers {
            _tweetAndUsers.value=it
        }
    }


    private fun fetchtweetsAndusers(onResult:(List<Pair<TweetModel,UserModel>>)->Unit){
        tweet.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = mutableListOf<Pair<TweetModel,UserModel>>()
                for(tweetsnapshot in snapshot.children){
                    val tweet = tweetsnapshot.getValue(TweetModel::class.java)
                    tweet.let{
                        fetchUserfromTweet(it!!){
                            user->
                            result.add(0,it to user)
                            if(result.size == snapshot.childrenCount.toInt()){
                                onResult(result)
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun fetchUserfromTweet(tweet:TweetModel,onResult: (UserModel)->Unit){
        db.getReference("users").child(tweet.userID)
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    val user = snapshot.getValue(UserModel::class.java)
                    user?.let(onResult)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }


}
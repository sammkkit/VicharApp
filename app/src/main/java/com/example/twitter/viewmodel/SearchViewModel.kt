package com.example.twitter.viewmodel

import android.content.Context
import android.net.Uri
import androidx.compose.animation.core.snap
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

class SearchViewModel : ViewModel() {
    private val db = FirebaseDatabase.getInstance()
    val users = db.getReference("users")


    private var _users = MutableLiveData<List<UserModel>>()
    val Users: MutableLiveData<List<UserModel>> = _users

    init {
        fetchUsers {
            _users.value = it
        }
    }

    private fun fetchUsers(onResult: (List<UserModel>) -> Unit) {
        users.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = mutableListOf<UserModel>()
                for (usersnapshot in snapshot.children) {
                    val user = usersnapshot.getValue(UserModel::class.java)
                    result.add(user!!)
                }
                onResult(result)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}
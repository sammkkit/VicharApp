package com.example.twitter.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPref {

    fun storeData(name:String,email:String,bio:String,userName:String,imageurl:String,context: Context){
        val sharedPreferences = context.getSharedPreferences("users",MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name",name)
        editor.putString("email",email)
        editor.putString("bio",bio)
        editor.putString("username",userName)
        editor.putString("imageurl",imageurl)
        editor.apply()
    }

    fun getUserName(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users",MODE_PRIVATE)
        return sharedPreferences.getString("username","")!!
    }
    fun getName(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users",MODE_PRIVATE)
        return sharedPreferences.getString("name","")!!
    }
    fun getemail(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users",MODE_PRIVATE)
        return sharedPreferences.getString("email","")!!
    }
    fun getbio(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users",MODE_PRIVATE)
        return sharedPreferences.getString("bio","")!!
    }
    fun getimage(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("users",MODE_PRIVATE)
        return sharedPreferences.getString("imageurl","")!!
    }

}
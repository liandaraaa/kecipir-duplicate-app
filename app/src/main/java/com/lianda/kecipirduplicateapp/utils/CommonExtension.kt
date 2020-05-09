package com.lianda.topstoryapp.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun AppCompatActivity.replaceFragment(layoutId:Int, fragment:Fragment, isBackStack:Boolean = false){
    supportFragmentManager
        .beginTransaction()
        .replace(layoutId, fragment)
        .addToBackStack(if(isBackStack) fragment.tag else null)
        .commit()
}
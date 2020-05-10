package com.lianda.topstoryapp.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout

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

fun ImageView.showImageUrl(context:Context, url:String){
    Glide.with(context).load(url).into(this)
}

fun ShimmerFrameLayout.showLoading(){
    this.startShimmer()
}

fun ShimmerFrameLayout.hideLoading(){
    this.stopShimmer()
    this.gone()
}
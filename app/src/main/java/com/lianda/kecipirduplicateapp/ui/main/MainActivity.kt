package com.lianda.kecipirduplicateapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.ui.home.HomeFragment
import com.lianda.kecipirduplicateapp.ui.placeholder.PlaceholderFragment
import com.lianda.kecipirduplicateapp.utils.getCurrentDate
import com.lianda.topstoryapp.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnvMain.setOnNavigationItemSelectedListener(this)

        showCurrentDate()
    }

    fun showCurrentDate(){
        tvDeliveryDate.text = getCurrentDate()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuHome -> replaceFragment(R.id.contentContainer, HomeFragment.newInstance())
            R.id.menuProfile -> replaceFragment(R.id.contentContainer, PlaceholderFragment.newInstance())
        }
        return true
    }

}

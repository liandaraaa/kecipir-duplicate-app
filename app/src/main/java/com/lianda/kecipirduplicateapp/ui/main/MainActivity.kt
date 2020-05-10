package com.lianda.kecipirduplicateapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lianda.kecipirduplicateapp.R
import com.lianda.kecipirduplicateapp.ui.home.HomeFragment
import com.lianda.kecipirduplicateapp.ui.placeholder.PlaceholderFragment
import com.lianda.kecipirduplicateapp.utils.getCurrentDate
import com.lianda.kecipirduplicateapp.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    companion object{
        const val HOME_PAGE = 0
        const val PROFILE_PAGE = 1
    }

    private var prevMenuItem:MenuItem?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnvMain.setOnNavigationItemSelectedListener(this)

        selectFragment(HomeFragment(), HOME_PAGE)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuHome -> selectFragment(HomeFragment(), HOME_PAGE)
            R.id.menuProfile -> selectFragment(PlaceholderFragment(), PROFILE_PAGE)
        }
        return true
    }

    private fun selectFragment(fragment: Fragment, position: Int){
        replaceFragment(R.id.contentContainer, fragment)
        changeMenuStatus(position)
    }

    private fun changeMenuStatus(position:Int){
        if (prevMenuItem != null){
            prevMenuItem?.isChecked = false
        }else{
            bnvMain.menu.getItem(HOME_PAGE).isChecked = true
        }

        bnvMain.menu.getItem(position).isChecked = true
        prevMenuItem = bnvMain.menu.getItem(position)
    }

    override fun onBackPressed() {
        if (bnvMain.selectedItemId == R.id.menuHome){
            finish()
        }else{
            selectFragment(HomeFragment(), HOME_PAGE)
        }
    }

}

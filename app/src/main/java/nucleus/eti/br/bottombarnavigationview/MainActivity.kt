package com.eti.nucleus.bottombarnavigationview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import nucleus.eti.br.bottombarnavigationview.R
import nucleus.eti.br.bottombarnavigationview.fragments.*
import nucleus.eti.br.bottombarnavigationview.helpers.BottomNavigationViewHelper

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.navigation)
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
       /* BottomNavigationViewHelper.removeTextLabel(bottomNavigationView)
        BottomNavigationViewHelper.sizeIcon(bottomNavigationView,resources.displayMetrics,28f)*/
        BottomNavigationViewHelper.addBadge(bottomNavigationView,this,3)

        supportFragmentManager.beginTransaction().replace(R.id.framelayout,Fragment3()).commit()
        bottomNavigationView.selectedItemId = R.id.menu_item3

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.menu_item1 -> selectedFragment = Fragment1()
                R.id.menu_item2 -> selectedFragment = Fragment2()
                R.id.menu_item3 -> selectedFragment = Fragment3()
                R.id.menu_item4 -> selectedFragment = Fragment4()
                R.id.menu_item5 -> selectedFragment = Fragment5()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framelayout, selectedFragment)
            transaction.commit()
            true
        }
    }

}

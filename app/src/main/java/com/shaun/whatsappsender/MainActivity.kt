package com.shaun.whatsappsender

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.iammert.library.readablebottombar.ReadableBottomBar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
  private var fragNumber=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar)
        setContentView(R.layout.activity_main)

        openFragment(MessageFragment())
        bottom_nav.setOnItemSelectListener(object : ReadableBottomBar.ItemSelectListener {
            override fun onItemSelected(index: Int) {
                fragNumber=index
                  if (index == 0) {
                 openFragment(MessageFragment())
                } else if (index == 1) {
                    openFragment((FragmentHistory()))
                }
            }
        })

    }

    private fun openFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(fragNumber==1)
           bottom_nav.selectItem(0)
        else
            finish()


    }
}
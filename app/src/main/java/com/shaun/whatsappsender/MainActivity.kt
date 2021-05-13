package com.shaun.whatsappsender

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NumberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProvider(this).get(NumberViewModel::class.java)

        openFragment(MessageFragment(), 0)

        viewModel.fragNumber.observe(this, Observer {

            if (it == 0) {
                openFragment(MessageFragment(), it)
            } else if (it == 1) {

                openFragment((FragmentHistory()), it)
            }
        })

        val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_message -> {
                    viewModel.fragNumber.value = 0
                    true

                }
                R.id.nav_history -> {
                    viewModel.fragNumber.value = 1
                    true
                }
                else ->{
                    false
                }
            }

        }

        bottom_nav.setOnNavigationItemSelectedListener(listener)


    }

    private fun openFragment(fragment: Fragment, index: Int) {
          val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)


        transaction.commit()
    }


}
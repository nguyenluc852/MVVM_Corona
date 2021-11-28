package com.s.basemvvm.ui.activity.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.s.basemvvm.R
import com.s.basemvvm.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var mNavController: NavController
    override fun navController(): NavController? = mNavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        mNavController = navHostFragment.navController
    }
}
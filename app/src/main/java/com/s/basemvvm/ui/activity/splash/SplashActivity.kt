package com.s.basemvvm.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.NavController
import com.s.basemvvm.R
import com.s.basemvvm.base.BaseActivity
import com.s.basemvvm.ui.activity.main.MainActivity

class SplashActivity : BaseActivity() {
    private lateinit var mNavController: NavController
    override fun navController(): NavController? = mNavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setTransparentStatusBar()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish()
        }, 2000)
    }
}
package com.applovin.enterprise.apps.demoapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.applovin.sdk.AppLovinSdkUtils
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppLovinSdkUtils.runOnUiThreadDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }, TimeUnit.SECONDS.toMillis(2))

        startForegroundService()
    }

    private fun startForegroundService() {
        val serviceIntent = Intent(this, AdiscopeForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("sooyeon", "startForegroundService")
            startForegroundService(serviceIntent)
        } else {
            Log.d("sooyeon", "startService")
            startService(serviceIntent)
        }
    }
}

package com.paras.flowerapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.paras.flowerapp.app.App
import com.paras.flowerapp.utils.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val SplashWaitTime: Long = 1000
class SplashScreenActivity:AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadUi()
        }
    }


    @Composable
    fun LoadUi() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LaunchedEffect(key1 = true) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(SplashWaitTime)
                    if (App.instance.sharedPrefUtils.getBoolean(Const.KEY_IS_LOGIN)){
                        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                        finish()
                    }else{
                        startActivity(Intent(this@SplashScreenActivity, LoginScreenActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}
package com.androiddevs.mvvmnewsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.androiddevs.mvvmnewsapp.interfaces.ConnectivityObserver
import com.androiddevs.mvvmnewsapp.util.NetworkConnectivityObserver

class SplashScreenActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            val status by connectivityObserver.observer().collectAsState(initial = ConnectivityObserver.Status.Unavailable)

                Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                    Text(text = "Network status: $status")
                }

        }
    }
}
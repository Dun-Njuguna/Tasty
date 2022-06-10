package com.dunk.eats.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dunk.eats.Greeting
import android.widget.TextView
import androidx.activity.compose.setContent
import com.dunk.eats.android.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}

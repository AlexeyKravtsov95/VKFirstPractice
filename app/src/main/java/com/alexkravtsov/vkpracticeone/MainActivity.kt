package com.alexkravtsov.vkpracticeone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alexkravtsov.vkpracticeone.navigation.AppRoot
import com.alexkravtsov.vkpracticeone.ui.theme.VKPracticeOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKPracticeOneTheme {
                AppRoot()
            }
        }
    }
}
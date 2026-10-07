package com.alexkravtsov.vkpracticeone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.alexkravtsov.vkpracticeone.screen.SecondScreen
import com.alexkravtsov.vkpracticeone.ui.theme.VKPracticeOneTheme

class SecondActivity : ComponentActivity() {
    companion object {
        const val KEY = "KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val incomingText = intent.getStringExtra(KEY)
        enableEdgeToEdge()
        setContent {
            VKPracticeOneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SecondScreen(
                        modifier = Modifier.padding(innerPadding),
                        text = incomingText
                    )
                }
            }
        }
    }
}
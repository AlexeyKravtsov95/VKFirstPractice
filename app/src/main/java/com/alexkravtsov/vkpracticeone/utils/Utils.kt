package com.alexkravtsov.vkpracticeone.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri
import com.alexkravtsov.vkpracticeone.SecondActivity

object Utils {
    fun openActivity(context: Context, text: String) {
        val intent = Intent(context, SecondActivity::class.java).apply {
            putExtra(SecondActivity.KEY, text)
        }
        context.startActivity(intent)
    }

    fun openDialer(context: Context, phoneNumber: String) {
        val phone = phoneNumber.trim().replace(Regex("[^0-9+]"), "")

        if (phone.isBlank() || phone == "+") {
            Toast.makeText(context, "Похоже, это не номер телефона", Toast.LENGTH_SHORT).show()
            return
        }

        val uri = "tel:$phone".toUri()
        val intent = Intent(Intent.ACTION_DIAL, uri)
        context.startActivity(intent)
    }

    fun openShare(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        context.startActivity(intent)
    }
}
package com.example.schoollocator.emailAPI

import YahooMail
import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun SendEmail(context: Context) {
    val emailSender = YahooMail("newbie_gwapo@yahoo.com", "iwjaqbscebejcvyd")

    CoroutineScope(Dispatchers.IO).launch {
        val success = emailSender.sendEmail(
            toEmail = "medinajrfrouen@gmail.com",
            subject = "Test Email",
            body = "This is a test email sent from Jetpack Compose."
        )

        withContext(Dispatchers.Main) {
            showToast(context, success)
        }
    }
}

fun showToast(context: Context, success: Boolean) {
    Toast.makeText(
        context,
        if (success) "Email sent successfully" else "Failed to send email",
        Toast.LENGTH_SHORT
    ).show()
}
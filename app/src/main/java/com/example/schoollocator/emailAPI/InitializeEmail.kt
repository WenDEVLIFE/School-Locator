package com.example.schoollocator.emailAPI

import android.content.Context
import android.widget.Toast

// Usage example in your activity or fragment
fun SendEmail(context: Context) {
    val apiKey = "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"
    val sendGridAPI = SendGridAPI(apiKey)

    val success = sendGridAPI.sendEmail(
        fromEmail = "newbie_gwapo@yahoo.com",
        toEmail = "medinajrfrouen@gmail.com",
        subject = "Test Email",
        body = "This is a test email sent from Jetpack Compose."
    )
    showToast(context, success)
}


fun showToast(context: Context, success: Boolean) {
    Toast.makeText(
        context,
        if (success) "Email sent successfully" else "Failed to send email",
        Toast.LENGTH_SHORT
    ).show()
}

package com.example.schoollocator.emailAPI

import com.sendgrid.*
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import com.sendgrid.helpers.mail.objects.Personalization
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

class SendGridAPI(private val apiKey: String) {
    private val client = OkHttpClient()

    fun sendEmail(fromEmail: String, toEmail: String, subject: String, body: String): Boolean {
        // Create email objects using the SendGrid helpers
        val from = Email(fromEmail)
        val to = Email(toEmail)
        val content = Content("text/plain", body)

        // Create a Mail object
        val mail = Mail()
        mail.setFrom(from)
        mail.setSubject(subject)
        mail.addContent(content)

        // Create a Personalization object
        val personalization = Personalization()
        personalization.addTo(to) // Add recipient to the Personalization object
        mail.addPersonalization(personalization) // Add the Personalization object to the mail

        // Create request body with JSON content
        val requestBody = RequestBody.create(
            "application/json".toMediaType(),
            mail.build()
        )

        // Create the request
        val request = Request.Builder()
            .url("https://api.sendgrid.com/v3/mail/send")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody)
            .build()

        return try {
            // Execute the request
            val response: Response = client.newCall(request).execute()
            // Check if the response indicates success
            response.isSuccessful
        } catch (ex: Exception) {
            ex.printStackTrace()
            false
        }
    }
}






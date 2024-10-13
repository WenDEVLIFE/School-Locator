import YahooMail
import android.content.Context
import android.widget.Toast
import com.example.schoollocator.emailAPI.yahookey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// This will send the email
fun SendEmail(context: Context) {
    // Show the initial Toast message on the main thread
    CoroutineScope(Dispatchers.Main).launch {
        Toast.makeText(context, "Sending email...", Toast.LENGTH_SHORT).show()
    }

    CoroutineScope(Dispatchers.IO).launch {
        val yahookey = yahookey()
        val username = yahookey.getUsername()
        val password = yahookey.getPassword()
        val emailSender = YahooMail(username, password)

        // Send the email
        val success = emailSender.sendEmail(
            toEmail = "medinajrfrouen@gmail.com",
            subject = "Test Email",
            body = "This is a test email sent from Jetpack Compose."
        )

        // Show the result Toast on the main thread
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

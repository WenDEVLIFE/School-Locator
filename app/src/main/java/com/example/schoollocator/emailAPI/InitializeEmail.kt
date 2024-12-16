import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.schoollocator.components.AlertDialog
import com.example.schoollocator.emailAPI.yahookey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// This will send the email
fun SendEmail(context: Context, onEmailSent: (Boolean) -> Unit) {
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

        // Notify the result
        withContext(Dispatchers.Main) {
            onEmailSent(success)

        }
    }
}

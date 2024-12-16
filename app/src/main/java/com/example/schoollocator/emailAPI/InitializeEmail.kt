import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.example.schoollocator.emailAPI.yahookey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun SendEmail(context: Context, email: String, code: String, onEmailSent: (Boolean) -> Unit) {
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
            toEmail = email,
            subject = "School Locator Registration Code",
            body = "The registration code is: $code"
        )

        // Notify the result
        withContext(Dispatchers.Main) {
            onEmailSent(success)

        }
    }
}

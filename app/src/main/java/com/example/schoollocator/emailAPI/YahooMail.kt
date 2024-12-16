import java.util.Properties
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class YahooMail(private val username: String, private val password: String) {

    fun sendEmail(toEmail: String, subject: String, body: String): Boolean {
        val props = Properties().apply {
            put("mail.smtp.auth", "true") // Enable authentication
            put("mail.smtp.starttls.enable", "true") // Enable STARTTLS
            put("mail.smtp.host", "smtp.mail.yahoo.com") // Yahoo SMTP server
            put("mail.smtp.port", "587") // Port for TLS
        }

        val session = Session.getInstance(props, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

        return try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress("SchoolLocator"))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail))
                setSubject(subject)
                setText(body)
            }

            Transport.send(message)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error: ${e.message}") // Log the error message
            false
        }
    }
}
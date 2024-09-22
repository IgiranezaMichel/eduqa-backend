package com.eduqa_backend.services;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.eduqa_backend.modal.User;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServices {
  @Autowired
  private JavaMailSender javaMailSender;

  public void sendEmail(String to, String subject, String body) throws UnsupportedEncodingException {
    try {
      MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
      mimeMailMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
      mimeMailMessage.addHeader("format", "flowed");
      mimeMailMessage.addHeader("Content-Transfer-Encoding", "8bit");
      mimeMailMessage.setReplyTo(InternetAddress.parse("no_reply@gmail.com", false));
      mimeMailMessage.setFrom(new InternetAddress("no_reply@gmail.com", "NoReply-Message"));
      mimeMailMessage.setSubject(subject);
      mimeMailMessage.setText(body, "UTF-8", "html");
      mimeMailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
      javaMailSender.send(mimeMailMessage);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Async
  public void sendUserHavingPasswordEmailConfirmation(User users) throws Exception {
    var message = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Welcome to Edu qa</title>
          <style>
            .body {
              background: linear-gradient(to right, #4fd1c5, #63b3ed);
              min-height: 100vh;
              margin: 0;
              font-family: sans-serif;
            }

            .container {
              max-width: 48rem; /* max-w-3xl */
              margin: 0 auto; /* mx-auto */
              padding: 2rem;
              color: black;
              font-weight: 600; /* font-semibold */
            }

            .header {
              display: flex;
              align-items: center;
              margin-bottom: 1.5rem; /* mb-6 */
              width: 100%;
              background-color: #ffffff;
              border-radius: 1rem; /* rounded-2xl */
              padding: 0.25rem; /* p-1 */
            }

            .header img {
              height: 3rem; /* h-12 */
              width: 2.5rem; /* w-10 */
              margin-right: 1rem; /* Adjusted for spacing */
            }

            .header .title {
              font-weight: 700; /* font-bold */
            }

            .h1 {
              font-size: 1.5rem; /* text-2xl */
              font-weight: 700; /* font-bold */
              margin-bottom: 1rem; /* mb-4 */
              text-align: center; /* text-center */
              color: #2d3748; /* text-gray-800 */
            }

            .p {
              font-size: 1.125rem; /* text-lg */
              color: #4a5568; /* text-gray-700 */
              margin-bottom: 1rem; /* mb-4 */
            }

            a {
              color: #3182ce; /* text-blue-500 */
            }

            a:hover {
              color: #2b6cb0; /* text-blue-600 */
            }

            .signature {
              font-size: 1.125rem; /* text-lg */
              color: #4a5568; /* text-gray-700 */
            }

          </style>
        </head>
        <body class="body">
          <div class="container">
            <div class="header">
              <img src="https://lh3.googleusercontent.com/d/1fAdwRZg5hbP7A5TtcAGrQYU1kZkYBrh2" alt="Company Logo" />
              <div class="title">
                Edu <br>Qua
              </div>
            </div>
            <h1 class="h1">Welcome to Edu qa!</h1>
            <p class="p">
              Dear  """
        + users.getName() +
        """
                </p>
                <p class="p">
                  We're excited to let you know that your account has been successfully created.
                </p>
                <p class="p">
                  Thank you for joining us! If you have any questions or need support, feel free to reach out to our support team at <a href="mailto:edu.qua@gmail.com">support@company.com</a>
                  or visit our website via <a href="http://www.eduqua.com">www.eduqa.com</a>
                </p>
                <p class="signature p">
                  Best regards,<br />
                  The Edu qa Team
                </p>
              </div>
            </body>
            </html>


                    """;
    this.sendEmail(users.getEmail(), "Welcome to Edu qa!", message);
  }

  @Async
  public void sendUserHavingNoPasswordEmailConfirmation(User users, String password) throws Exception {
    var message = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Welcome to Edu qa</title>
          <style>
            .body {
              background: linear-gradient(to right, #4fd1c5, #63b3ed);
              min-height: 100vh;
              margin: 0;
              font-family: sans-serif;
            }

            .container {
              max-width: 48rem; /* max-w-3xl */
              margin: 0 auto; /* mx-auto */
              padding: 2rem;
              color: black;
              font-weight: 600; /* font-semibold */
            }

            .header {
              display: flex;
              align-items: center;
              margin-bottom: 1.5rem; /* mb-6 */
              width: 100%;
              background-color: #ffffff;
              border-radius: 1rem; /* rounded-2xl */
              padding: 0.25rem; /* p-1 */
            }

            .header img {
              height: 3rem; /* h-12 */
              width: 2.5rem; /* w-10 */
              margin-right: 1rem; /* Adjusted for spacing */
            }

            .header .title {
              font-weight: 700; /* font-bold */
            }

            .h1 {
              font-size: 1.5rem; /* text-2xl */
              font-weight: 700; /* font-bold */
              margin-bottom: 1rem; /* mb-4 */
              text-align: center; /* text-center */
              color: #2d3748; /* text-gray-800 */
            }

            .p {
              font-size: 1.125rem; /* text-lg */
              color: #4a5568; /* text-gray-700 */
              margin-bottom: 1rem; /* mb-4 */
            }

            a {
              color: #3182ce; /* text-blue-500 */
            }

            a:hover {
              color: #2b6cb0; /* text-blue-600 */
            }

            .signature {
              font-size: 1.125rem; /* text-lg */
              color: #4a5568; /* text-gray-700 */
            }

          </style>
        </head>
        <body class="body">
          <div class="container">
            <div class="header">
              <img src="https://lh3.googleusercontent.com/d/1fAdwRZg5hbP7A5TtcAGrQYU1kZkYBrh2" alt="Company Logo" />
              <div class="title">
                Amal Health <br>Africa
              </div>
            </div>
            <h1 class="h1">Welcome to Edu qa!</h1>
            <p class="p">
              Dear  """
        + users.getName() +
        """
                </p>
                <p class="p">
                  We're excited to let you know that your account has been successfully created.
                </p>
                <p class="p">
            below are the credentials you can use to login in to your account.
            <p> <b>Email </b>
            """
        + users.getEmail() +
        """
            </p>
            <p> <b>Password </b>
            """
        + password +
        """
            </p>
           if you have any question you can visit our website via <a href="http://www.eduqa.com">www.eduqa.com</a>
                </p>
                <p class="signature p">
                  Best regards,<br />
                  The Edu qa Team
                </p>
              </div>
            </body>
            </html>


                    """;
    this.sendEmail(users.getEmail(), "Welcome to Edu qa!", message);
  }

  public void receivedFreelancingApplication(User users) throws UnsupportedEncodingException {
    var message = """
                <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Application Received - Edu qa</title>
          <style>
            .body {
              background: linear-gradient(to right, #279186, #226696);
              min-height: 100vh;
              margin: 0;
              font-family: Arial, sans-serif;
              display: flex;
              justify-content: center;
              align-items: center;
            }

            .container {
              max-width: 48rem; /* max-w-3xl */
              margin: 0 auto; /* mx-auto */
              padding: 2rem; /* p-8 */
              background-color: #ffffff; /* Background for content area */
              border-radius: 1rem; /* rounded-2xl */
              box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Shadow for card effect */
              font-weight: 600; /* font-semibold */
            }

            .header {
              display: flex;
              align-items: center;
              margin-bottom: 1.5rem; /* mb-6 */
              background-color: #ffffff;
              border-radius: 1rem; /* rounded-2xl */
              padding: 0.5rem; /* p-2 for better spacing */
            }

            .header .img {
              height: 3rem; /* h-12 */
              width: 2.5rem; /* w-10 */
              margin-right: 1rem; /* Adjusted for spacing */
            }

            .header .title {
              font-weight: 700; /* font-bold */
              color: #2d3748; /* Dark text color */
            }

            .h1 {
              font-size: 1.75rem; /* text-3xl for emphasis */
              font-weight: 700; /* font-bold */
              margin-bottom: 1rem; /* mb-4 */
              text-align: center; /* text-center */
              color: #2d3748; /* Dark text color */
            }

            .p {
              font-size: 1.125rem; /* text-lg */
              color: #1a1e26; /* text-gray-700 */
              margin-bottom: 1rem; /* mb-4 */
            }

            a {
              color: #3182ce; /* text-blue-500 */
              text-decoration: none; /* Remove underline */
            }

            a:hover {
              color: #2b6cb0; /* text-blue-600 */
              text-decoration: underline; /* Underline on hover for better UX */
            }

            .signature {
              font-size: 1.125rem; /* text-lg */
              color: #4a5568; /* text-gray-700 */
            }

            .footer {
              margin-top: 2rem; /* Space before the footer */
              text-align: center; /* Center-align the footer text */
              color: #4a5568; /* Text color */
            }
          </style>
        </head>
        <body class="body">
          <div class="container">
            <div class="header">
              <img class="img" src="https://lh3.googleusercontent.com/d/1fAdwRZg5hbP7A5TtcAGrQYU1kZkYBrh2" alt="Company Logo" />
              <div class="title">
                Amal Health <br>Africa
              </div>
            </div>
            <h1 class="h1">Your Application Has Been Received!</h1>
            <p class="p">
              Dear  <span> </span>"""
        + users.getName() +
        """
                </p>
                <p class="p">
                  Thank you for applying for a freelancing position with Edu qa. We have received your application and it is currently under review.
                </p>
                <p class="p">
                  We appreciate your interest in joining our team and will get back to you as soon as possible. If you have any questions or need further information, please don't hesitate to reach out to us at <a href="mailto:amal.healthafrica@gmail.com">amal.healthafrica@gmail.com</a>.
                </p >
                <p class="p">
                  Thank you for your patience and understanding.
                </p>
                <p class="signature p">
                  Best regards,<br />
                  The Edu qa Team
                </p>
                <div class="footer p">
                  For more information, visit our website at <a href="http://www.eduqa.com">www.eduqa.com</a>
                </div>
              </div>
            </body>
            </html>
                    """;
    this.sendEmail(users.getEmail(), "Application of freelancing", message);

  }
}

package com.disneyapiproject.emailsender;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class ExampleSenderServiceImpl implements IEmailSenderService {


    @Autowired
    private Environment environment;
    @Value("${spring.sendgrid.email.sender}")
    private String emailSender;
    @Value("${spring.sendgrid.email.enabled}")
    private boolean enable;

    public void sendWelcomeEmailTo(String to) {

        String apiKey = environment.getProperty("EMAIL_API_KEY");

        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain",
                "Welcome to my project!"
        );
        String subject = "Disney API";

        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sendGrid = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {System.out.println("Error trying to send the email.");}
    }
}

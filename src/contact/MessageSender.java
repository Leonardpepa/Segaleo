package contact;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;

import resources.TextResources;

/*
 * The MessageSender class is used to send an email to the user based on their action
 * 
 * Explaining the variables used to declare the method
 * 
 * email: the recipients email
 * 
 * isContact: boolean variable to determine the kind of email that needs to be sent to the user
 * 
 * password: in case the isContact variable is false, the email being sent is the reminder of the users password.
 * In this instance the password is searched on the ForgotPasswordWindow and is given here as a variable, 
 * so the email containing the password found is being sent.
 */
public class MessageSender {

	public void sendEmail(String email, boolean isContact, String password) {
		String from = "segaleouom@gmail.com";
		String to = email;

		// Get the session object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");

		try {
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, "segaleo123");
				}
			});

			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			
			if (isContact) {
				msg.setSubject("We have received your message");
				msg.setText(
						"Hello,\n\nThanks for submitting your request.\n\nOur support team will get back to you as soon as possible.\n\nSegaleo");
			} else {
				msg.setSubject("Password Reminder");
				msg.setText("Hello,\n\n Your password is: " + password + "\n\nSegaleo");

			}

			msg.setSentDate(new Date());
			Transport.send(msg);
			
			if(isContact) {
				JOptionPane.showMessageDialog(null, TextResources.emailSent);
			}else {
				JOptionPane.showMessageDialog(null, TextResources.reminderSent);
			}
				
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, TextResources.networkError, "Error", JOptionPane.WARNING_MESSAGE);
		}

	}

}

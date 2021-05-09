package contact;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  

public class MessageSender {
	
	public void sendEmail(String email, String name){  
		String from = "segaleouom@gmail.com";  
	      String to = email; 
	      String host = "localhost";  
	      
	     //Get the session object  
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
	      
	      try{
	          Session session = Session.getDefaultInstance(props, 
	                              new Authenticator(){
	                                 protected PasswordAuthentication getPasswordAuthentication() {
	                                    return new PasswordAuthentication(from, "segaleo123");
	                                 }});

	       // -- Create a new message --
	          Message msg = new MimeMessage(session);

	       // -- Set the FROM and TO fields --
	          msg.setFrom(new InternetAddress(from));
	          msg.setRecipients(Message.RecipientType.TO, 
	                            InternetAddress.parse(to,false));
	          msg.setSubject("We have received your message");
	          msg.setText("Hello " + name + ",\n\nThanks for submitting your request.\n\nOur support team will get back to you as soon as possible.\n\nSegaleo");
	          msg.setSentDate(new Date());
	          Transport.send(msg);
	          System.out.println("Message sent.");
	        }catch (MessagingException e){ 
	          System.out.println("Error: " + e);
	        } 

	}
}

package contact;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;   
  

public class MessageSender {
	
	public void sendEmail(String email, boolean isContact, String password){  
		String from = "segaleouom@gmail.com";  
	      String to = email; 
	      
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
	          if(isContact) {
	        	  msg.setSubject("We have received your message");
	        	  msg.setText("Hello,\n\nThanks for submitting your request.\n\nOur support team will get back to you as soon as possible.\n\nSegaleo");	        	  
	          }
	          else {
	        	  msg.setSubject("Password Reminder");
	        	  msg.setText("Hello,\n\n Your password is: " + password 
	        	  		+ "\n\nSegaleo");	        	  
	     
	          }

	          msg.setSentDate(new Date());
	          Transport.send(msg);
	          System.out.println("Message sent.");
	        }catch (MessagingException e){ 
	          System.out.println("Error: " + e);
	        } 

	}
	
}

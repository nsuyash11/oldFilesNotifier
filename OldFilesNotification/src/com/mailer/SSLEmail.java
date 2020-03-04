package com.mailer;

import java.io.FileReader;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSLEmail {


	public static void dispatchEmail(String msgBody) {

		try {
	
			//String propFilePath = "C:\\Users\\s.ratnakar.nachankar\\Downloads\\javaLearning\\jars\\myJars\\mailSettings.properties";
			String propFilePath = "mailSettings.properties";
			//String propFilePath = "src/com/resources/mailSettings.properties";
			FileReader inputFile = new FileReader(propFilePath);
			Properties props = new Properties();

			// load a properties file
			props.load(inputFile);

			final String fromEmail = props.getProperty("fromEmail");
			final String password = props.getProperty("password");
			final String toEmail = props.getProperty("toEmail");			

			System.out.println("\nSSLEmail Start");

			Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};

			Session session = Session.getDefaultInstance(props, auth);
			System.out.println("Session created");
			
			String mailSubject = "Old Files Notification";
			
			EmailUtil.sendEmail(session, toEmail, mailSubject, msgBody);

		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
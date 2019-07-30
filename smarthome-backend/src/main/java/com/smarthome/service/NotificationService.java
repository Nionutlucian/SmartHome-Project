package com.smarthome.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthome.model.Notification;
import com.smarthome.repository.NotificationRepository;
import com.smarthome.utility.StaticData;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

@Service
public class NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Transactional
	public void sendEmail(String emailToSend, String type) {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(StaticData.CLEVER_HOME_EMAIL, StaticData.CLEVER_HOME_PASSWORD);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(StaticData.CLEVER_HOME_EMAIL));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToSend));
			switch (type) {
			case "door":
				message.setSubject("Notificare usa");
				message.setText("Salut " + emailToSend + "," + "\n\n S-a produs o actiune asupra usei dumneavoastra!"
						+ "Va rugam sa verificati!" + "\n\n O zi buna," + "\n Echipa Clever Home");
				Transport.send(message);
				break;
			case "gas":
				message.setSubject("Notificare senzor gaze");
				message.setText("Salut " + emailToSend + "," + "\n\n Senzorul de gaze a depasit valoarea normala!"
						+ "Va rugam sa verificati!" + "\n\n O zi buna," + "\n Echipa Clever Home");
				Transport.send(message);
				break;
			}
			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void sensSMSNotification(String phoneNumber, String type) {
		Twilio.init(StaticData.ACCOUNT_SID, StaticData.AUTH_TOKEN);

		switch (type) {
		case "door":
			com.twilio.rest.api.v2010.account.Message messageDoor = com.twilio.rest.api.v2010.account.Message
					.creator(new PhoneNumber(phoneNumber), // to
							new PhoneNumber(StaticData.CLEVER_HOME_PHONE_NUMBER), // from
							"Buna ziua!S-a produs o actiune asupra usei dumneavoastra!Va rugam sa verificati!")
					.create();
			break;
		case "gas":
			com.twilio.rest.api.v2010.account.Message messageGas = com.twilio.rest.api.v2010.account.Message
					.creator(new PhoneNumber(phoneNumber), // to
							new PhoneNumber(StaticData.CLEVER_HOME_PHONE_NUMBER), // from
							"Buna ziua!Senzorul de gaze a depasit valoarea normala!Va rugam sa verificati!")
					.create();
			break;
		}
	}

	@Transactional
	public Notification updateNotification(int doorNotificationPhone, int doorNotificationEmail,
			int gasNotificationPhone, int gasNotificationEmail, int id) {
		Notification notification = new Notification();
		notification.setDoorNotificationEmail(doorNotificationEmail);
		notification.setDoorNotificationPhone(doorNotificationPhone);
		notification.setGasNotificationEmail(gasNotificationEmail);
		notification.setGasNotificationPhone(gasNotificationPhone);
		notificationRepository.updateNotification(doorNotificationPhone, doorNotificationEmail, gasNotificationPhone,
				gasNotificationEmail, id);
		return notification;

	}

	@Transactional
	public Notification getNotificationStatus(int houseId) {
		return notificationRepository.getNotificationByHouseId(houseId);
	}
}

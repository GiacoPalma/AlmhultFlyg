package app;

import java.awt.Frame;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import swing.AdminBookings;

public class SendMailSSL extends Thread {
	Thread runner;
	public User user;
	public Airport depAirport;
	public Airport destAirport;
	public Route route;
	public boolean sent = false;
	public int id;

	public SendMailSSL(String str, User user, Airport depAirport,
			Airport destAirport, Route route, int id) {
		super(str);
		this.depAirport = depAirport;
		this.destAirport = destAirport;
		this.user = user;
		this.route = route;
		this.id = id;
	}

	public void run() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("almhultflyg",
								"wr5WaZuS");
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("almhultflyg@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(user.getEmail()));
			message.setSubject("Bekräftelse av bokning");
			message.setText("Hej "
					+ user.getFirst_name()
					+ " "
					+ user.getLast_name()
					+ "!\n\nDetta är en bekräftelse av din bokning hos Älmhult Flyg.\n\nDu har bokat resan:\n\nFrån: "
					+ depAirport.getName() + " Till: " + destAirport.getName()
					+ " Avgång: " + route.getDepature_date() + " Framme: "
					+ route.getDestination_date() + " Pris: "
					+ route.getPrice());

			Transport.send(message);
			Database.MakeConfirmed(id);
			Frame[] windowToClose = JFrame.getFrames();
			for (int i = 0; i < windowToClose.length; i++) {
				windowToClose[i].dispose();
			}
			AdminBookings adminBookings = new AdminBookings();
			adminBookings.setVisible(true);
			JOptionPane.showMessageDialog(new JFrame(),
					"Bokningen har bekräftats", "Dialog",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getCause(), "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
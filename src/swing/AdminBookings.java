package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.DefaultListModel;

import app.Booking;
import app.Database;
import app.MailCounter;
import app.SendMailSSL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AdminBookings extends JFrame {

	private JPanel contentPane;
	private List<Booking> userBookings = new ArrayList<Booking>();
	private DefaultListModel listModel = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminBookings frame = new AdminBookings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminBookings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		final JList list = new JList(listModel);
		list.setBounds(6, 51, 879, 392);
		contentPane.add(list);

		JButton btnNewButton = new JButton("Bekr\u00E4fta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection[] = list.getSelectedIndices();
				MailCounter mailCounter = new MailCounter();
				mailCounter.setNumberOfMailsToSend(selection.length);
				for (int j = 0; j < selection.length; j++) {
					new SendMailSSL("mail", userBookings.get(selection[j])
							.getUser(), userBookings.get(selection[j])
							.getDepAirport(), userBookings.get(selection[j])
							.getDestAirport(), userBookings.get(selection[j])
							.getRoute(),
							userBookings.get(selection[j]).getId(),
							mailCounter, AdminBookings.this).start();
				}

			}
		});
		btnNewButton.setBounds(897, 51, 127, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Tillbaka");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AdminBookings.this.setVisible(false);

				AdminMenu adminMenu = new AdminMenu();
				adminMenu.setVisible(true);

			}
		});
		btnNewButton_1.setBounds(897, 81, 127, 29);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("Obekr\u00E4ftade bokningar:");
		lblNewLabel.setBounds(6, 20, 179, 16);
		contentPane.add(lblNewLabel);

		userBookings = Database.getAllUserBookings();

		for (int i = 0; i < userBookings.size(); i++) {
			listModel.addElement(userBookings.get(i).depAirport.getName()
					+ " - " + userBookings.get(i).destAirport.getName()
					+ " AvgŒr: " + userBookings.get(i).route.getDepature_date()
					+ " Framme: "
					+ userBookings.get(i).route.getDestination_date()
					+ " Pris: " + userBookings.get(i).route.getPrice()
					+ " Namn: " + userBookings.get(i).user.getFirst_name()
					+ " " + userBookings.get(i).user.getLast_name() + " Mail: "
					+ userBookings.get(i).user.getEmail());
		}
	}
}

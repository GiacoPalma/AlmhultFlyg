package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Booking;
import app.Database;
import app.Route;
import app.User;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ShowUser extends JFrame {

	private JPanel contentPane;
	private List<Booking> flightBookings = new ArrayList<Booking>();
	private DefaultListModel listModel = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowUser frame = new ShowUser(null);
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
	public ShowUser(final Route route) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JList list = new JList(listModel);
		list.setBounds(38, 60, 522, 317);
		contentPane.add(list);

		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DeleteEditFlight deleteEditFlight = new DeleteEditFlight();
				deleteEditFlight.setVisible(true);
			}
		});
		btnTillbaka.setBounds(592, 94, 117, 29);
		contentPane.add(btnTillbaka);

		JButton btnTaBort = new JButton("Ta bort");
		btnTaBort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selection[] = list.getSelectedIndices();
				for (int i = 0; i < selection.length; i++) {
					Booking booking = new Booking();
					booking = flightBookings.get(selection[i]);
					Database.deleteUserFromRoute(booking.getId());
				}
				dispose();
				ShowUser showUser = new ShowUser(route);
				showUser.setVisible(true);

			}
		});
		btnTaBort.setBounds(592, 55, 117, 29);
		contentPane.add(btnTaBort);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(38, 21, 340, 16);
		flightBookings = Database.getAllBookingsByBooking(route);
		if (flightBookings.size() == 0) {
			lblNewLabel
					.setText("Det finns inga passagerare bokade pŒ den hŠr rutten.");
		} else {
			for (int i = 0; i < flightBookings.size(); i++) {
				listModel.addElement(flightBookings.get(i).user.getFirst_name()
						+ " " + flightBookings.get(i).user.getLast_name() + " "
						+ flightBookings.get(i).user.getEmail());
			}
			lblNewLabel.setText("Passagerare:");
		}

		contentPane.add(lblNewLabel);

	}
}

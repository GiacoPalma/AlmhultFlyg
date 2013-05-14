package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Booking;
import app.Database;
import app.User;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyBookings extends JFrame {

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
					MyBookings frame = new MyBookings(null);
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
	public MyBookings(final User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JList list = new JList(listModel);
		list.setBounds(24, 50, 688, 206);
		contentPane.add(list);
		
		JLabel lblBokingarFr = new JLabel("Bokingar f\u00F6r " + user.first_name + " " + user.last_name);
		lblBokingarFr.setBounds(24, 16, 219, 16);
		contentPane.add(lblBokingarFr);
		
		JButton btnAvboka = new JButton("Avboka");
		btnAvboka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Booking bookingToDelete = new Booking();
				int i = list.getSelectedIndex();
				bookingToDelete = userBookings.get(i);
				boolean removed = Database.RemoveBooking(bookingToDelete.id);
				if(removed){
					JOptionPane.showMessageDialog(new JFrame(),
							"Flygningen har tagits bort", "Dialog",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
					MyBookings newMyBookings = new MyBookings(user);
					newMyBookings.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"NŒgontingting gick fel.",
							"Dialog", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAvboka.setBounds(735, 50, 117, 29);
		contentPane.add(btnAvboka);
		
		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				UserMenu userMenu = new UserMenu(user);
				userMenu.setVisible(true);
			}
		});
		btnTillbaka.setBounds(735, 87, 117, 29);
		contentPane.add(btnTillbaka);
		
		userBookings = Database.getAllBookings(user);
		
		for(int i=0; i<userBookings.size(); i++){
			listModel.addElement(userBookings.get(i).depAirport.getName() + " - " + userBookings.get(i).destAirport.getName() + " AvgŒr: " + userBookings.get(i).route.getDepature_date() + " Framme: " + userBookings.get(i).route.getDestination_date() + " Pris: " + userBookings.get(i).route.getPrice());
		}
		
		
	}
}

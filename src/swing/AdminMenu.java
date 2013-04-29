package swing;
import app.Database;
import app.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMenu extends JFrame {

	private JPanel contentPane;
	public Database DB = new Database();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
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
	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_signout = new JButton("Logga ut");
		btn_signout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login Login = new Login();
				Login.frame.setVisible(true);
				setVisible(false);
			}
		});
		btn_signout.setBounds(335, 11, 89, 23);
		contentPane.add(btn_signout);
		
		JButton btn_users = new JButton("Konton");
		btn_users.setBounds(10, 88, 115, 23);
		contentPane.add(btn_users);
		
		JButton btn_airports = new JButton("Flygplatser");
		btn_airports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AirportSwing AirportSwing = new AirportSwing();
				AirportSwing.setVisible(true);
				setVisible(false);
			}
		});
		btn_airports.setBounds(10, 122, 115, 23);
		contentPane.add(btn_airports);
		
		JButton btn_flights = new JButton("Flygningar");
		btn_flights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FlightSwing flightSwing = new FlightSwing();
				flightSwing.setVisible(true);
				setVisible(false);
			}
		});
		btn_flights.setBounds(10, 156, 115, 23);
		contentPane.add(btn_flights);
		
		JLabel lbl_handle = new JLabel("Hantera");
		lbl_handle.setBounds(10, 61, 46, 14);
		contentPane.add(lbl_handle);
		
		JLabel lbl_user_lable = new JLabel("Inloggad som:");
		lbl_user_lable.setBounds(10, 11, 89, 14);
		contentPane.add(lbl_user_lable);
		
		JLabel lbl_user = new JLabel("");
		lbl_user.setBounds(109, 11, 89, 14);
		contentPane.add(lbl_user);
	}
}

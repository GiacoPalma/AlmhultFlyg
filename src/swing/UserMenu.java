package swing;
import app.Database;
import app.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserMenu extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMenu frame = new UserMenu(null);
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
	public UserMenu(final User user) {
		if(Database.current_user.equals("")){
			JOptionPane.showMessageDialog(contentPane, "Du måste vara inloggad!", "Warning",
			        JOptionPane.WARNING_MESSAGE);
			Login Login = new Login();
			Login.frmAlmhultAirlinesFlygbokning.setVisible(true);
			setVisible(false);
		} else {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			
			JButton btn_signout = new JButton("Logga ut");
			btn_signout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Login Login = new Login();
					Login.frmAlmhultAirlinesFlygbokning.setVisible(true);
					setVisible(false);
				}
			});
			btn_signout.setBounds(335, 11, 89, 23);
			contentPane.add(btn_signout);
			
			JLabel lbl_user_lable = new JLabel("Inloggad som:");
			lbl_user_lable.setBounds(10, 12, 180, 23);
			contentPane.add(lbl_user_lable);
			
			JLabel lbl_user = new JLabel(Database.current_user);
			lbl_user.setForeground(Color.BLUE);
			lbl_user.setBounds(105, 12, 198, 23);
			
			contentPane.add(lbl_user);
			
			JLabel lbl_choice = new JLabel("G\u00F6r ditt val");
			lbl_choice.setBounds(10, 89, 69, 14);
			contentPane.add(lbl_choice);
			
			JButton btn_book_flight = new JButton("Boka flyg");
			btn_book_flight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BookSwing BookSwing = new BookSwing(user);
					BookSwing.setVisible(true);
					setVisible(false);
				}
			});
			btn_book_flight.setBounds(10, 114, 127, 23);
			contentPane.add(btn_book_flight);
			
			JButton btn_flights = new JButton("Mina bokningar");
			btn_flights.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MyBookings myBookings = new MyBookings(user);
					myBookings.setVisible(true);
					dispose();
				}
			});
			btn_flights.setBounds(10, 148, 127, 23);
			contentPane.add(btn_flights);
			
			JButton btn_account = new JButton("Redigera konto");
			btn_account.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btn_account.setBounds(10, 182, 127, 23);
			contentPane.add(btn_account);
		}
	}
}

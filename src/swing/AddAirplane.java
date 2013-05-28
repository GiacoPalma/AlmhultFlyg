package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

import app.Database;

import java.awt.Font;

public class AddAirplane extends JFrame {

	private JPanel contentPane;
	private JTextField modelField;
	private JTextField seatsField;
	private JTextField fuelField;
	private JTextField travelspeedField;
	public Database DB = new Database();//

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAirplane frame = new AddAirplane();
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
	public AddAirplane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditAirplane editAirplane = new EditAirplane();
				editAirplane.setVisible(true);
			}
		});
		btnTillbaka.setBounds(45, 206, 89, 23);
		contentPane.add(btnTillbaka);
		
		JButton btnSkapa = new JButton("Skapa");
		btnSkapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int travelSpeed = Integer.valueOf(travelspeedField.getText());
				int fuel = Integer.valueOf(fuelField.getText());
				int seats = Integer.valueOf(seatsField.getText());
				String ret = DB.addAirplane(modelField.getText(), seats, fuel, travelSpeed);
				JOptionPane.showMessageDialog(null, ret);
			}
		});
		btnSkapa.setBounds(285, 206, 89, 23);
		contentPane.add(btnSkapa);
		
		modelField = new JTextField();
		modelField.setBounds(144, 56, 114, 20);
		contentPane.add(modelField);
		modelField.setColumns(10);
		
		seatsField = new JTextField();
		seatsField.setBounds(144, 87, 114, 20);
		contentPane.add(seatsField);
		seatsField.setColumns(10);
		
		fuelField = new JTextField();
		fuelField.setBounds(144, 118, 114, 20);
		contentPane.add(fuelField);
		fuelField.setColumns(10);
		
		travelspeedField = new JTextField();
		travelspeedField.setBounds(144, 149, 114, 20);
		contentPane.add(travelspeedField);
		travelspeedField.setColumns(10);
		
		JLabel lblModell = new JLabel("Modell:");
		lblModell.setBounds(45, 59, 89, 14);
		contentPane.add(lblModell);
		
		JLabel lblPlatser = new JLabel("Platser:");
		lblPlatser.setBounds(45, 90, 89, 14);
		contentPane.add(lblPlatser);
		
		JLabel lblBrnslekm = new JLabel("Br√§nsle/km:");
		lblBrnslekm.setBounds(45, 121, 89, 14);
		contentPane.add(lblBrnslekm);
		
		JLabel lblFärdhastighet = new JLabel("Färdhastighet:");
		lblFärdhastighet.setBounds(45, 152, 89, 14);
		contentPane.add(lblFärdhastighet);
		
		JLabel lblNewLabel = new JLabel("L√§gg till nytt flygplan");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(45, 11, 175, 34);
		contentPane.add(lblNewLabel);
	}
}

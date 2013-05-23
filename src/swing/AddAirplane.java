package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class AddAirplane extends JFrame {

	private JPanel contentPane;
	private JTextField modelField;
	private JTextField seatsField;
	private JTextField fuelField;
	private JTextField travelspeedField;

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
		btnSkapa.setBounds(285, 206, 89, 23);
		contentPane.add(btnSkapa);

		modelField = new JTextField();
		modelField.setBounds(134, 56, 86, 20);
		contentPane.add(modelField);
		modelField.setColumns(10);

		seatsField = new JTextField();
		seatsField.setBounds(134, 87, 86, 20);
		contentPane.add(seatsField);
		seatsField.setColumns(10);

		fuelField = new JTextField();
		fuelField.setBounds(134, 118, 86, 20);
		contentPane.add(fuelField);
		fuelField.setColumns(10);

		travelspeedField = new JTextField();
		travelspeedField.setBounds(134, 149, 86, 20);
		contentPane.add(travelspeedField);
		travelspeedField.setColumns(10);

		JLabel lblModell = new JLabel("Modell:");
		lblModell.setBounds(45, 59, 79, 14);
		contentPane.add(lblModell);

		JLabel lblPlatser = new JLabel("Platser:");
		lblPlatser.setBounds(45, 90, 79, 14);
		contentPane.add(lblPlatser);

		JLabel lblBrnslekm = new JLabel("Bränsle/km:");
		lblBrnslekm.setBounds(45, 121, 79, 14);
		contentPane.add(lblBrnslekm);

		JLabel lblFärdhastighet = new JLabel("Färdhastighet:");
		lblFärdhastighet.setBounds(45, 152, 79, 14);
		contentPane.add(lblFärdhastighet);
	}

}
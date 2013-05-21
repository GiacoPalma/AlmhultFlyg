package swing;

import app.Database;
import app.EmailValidator;
import app.PhoneValidator;
import app.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Scrollbar;

public class EditAirplane extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JTextField modelField;
	private JTextField seatsField;
	private JTextField fuelField;
	private JTextField travelspeedField;
	private DefaultListModel listModel = new DefaultListModel(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAirplane frame = new EditAirplane();
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
	public EditAirplane() {
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
				AdminMenu adminMenu = new AdminMenu();
				adminMenu.setVisible(true);
			}
		});
		btnTillbaka.setBounds(208, 228, 89, 23);
		contentPane.add(btnTillbaka);
		
		list = new JList(listModel);
		list.setBounds(10, 11, 178, 213);
		contentPane.add(list);
		
		modelField = new JTextField();
		modelField.setBounds(338, 9, 86, 20);
		contentPane.add(modelField);
		modelField.setColumns(10);
		
		seatsField = new JTextField();
		seatsField.setBounds(338, 40, 86, 20);
		contentPane.add(seatsField);
		seatsField.setColumns(10);
		
		fuelField = new JTextField();
		fuelField.setBounds(338, 71, 86, 20);
		contentPane.add(fuelField);
		fuelField.setColumns(10);
		
		travelspeedField = new JTextField();
		travelspeedField.setBounds(338, 102, 86, 20);
		contentPane.add(travelspeedField);
		travelspeedField.setColumns(10);
		
		JButton btnUppdatera = new JButton("Uppdatera");
		btnUppdatera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUppdatera.setBounds(323, 133, 101, 23);
		contentPane.add(btnUppdatera);
		
		JButton btnTaBort = new JButton("Ta bort");
		btnTaBort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTaBort.setBounds(323, 167, 101, 23);
		contentPane.add(btnTaBort);
		
		JButton btnSkapaNy = new JButton("Skapa ny");
		btnSkapaNy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddAirplane addAirplane = new AddAirplane();
				addAirplane.setVisible(true);
			}
		});
		btnSkapaNy.setBounds(323, 201, 101, 23);
		contentPane.add(btnSkapaNy);
		
		JLabel lblModel = new JLabel("Modell:");
		lblModel.setBounds(250, 12, 78, 14);
		contentPane.add(lblModel);
		
		JLabel lblPlatser = new JLabel("Platser:");
		lblPlatser.setBounds(250, 43, 78, 14);
		contentPane.add(lblPlatser);
		
		JLabel lblBrnsleKm = new JLabel("Bränsle/km:");
		lblBrnsleKm.setBounds(250, 74, 78, 14);
		contentPane.add(lblBrnsleKm);
		
		JLabel lblFärdhastighet = new JLabel("Färdhastighet:");
		lblFärdhastighet.setBounds(250, 105, 78, 14);
		contentPane.add(lblFärdhastighet);
	}
	public JList getList() {
		return list;
	}
}

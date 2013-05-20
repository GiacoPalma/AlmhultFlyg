package swing;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import app.Airport;
import app.Database;
import app.Flight;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;


public class EditFlight {
	
	public Database DB = new Database();
	private static ArrayList<Flight> flightlist = new ArrayList<Flight>();
	private DefaultListModel listModel = new DefaultListModel();
	private List<Flight> flights = new ArrayList<Flight>();
	private Flight flight;
	private String inputDeptDateFormated = null;
	
	

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditFlight window = new EditFlight();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditFlight() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		flightlist = (ArrayList<Flight>) DB.getAllFlights();
		for (int i = 0; i < flightlist.size(); i++) {
			comboBox.addItem(flightlist.get(i).id);
		}
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(37, 124, 141, 25);
		frame.getContentPane().add(comboBox);
		
		JList list = new JList();
		
		list.setBounds(243, 49, 163, 173);
		frame.getContentPane().add(list);
		
		JLabel lblFlygningar = new JLabel("Fixar med flygningarna");
		lblFlygningar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFlygningar.setBounds(37, 11, 151, 25);
		frame.getContentPane().add(lblFlygningar);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(37, 63, 141, 25);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblFlygningar_1 = new JLabel("Flygningar:");
		lblFlygningar_1.setBounds(47, 109, 61, 14);
		frame.getContentPane().add(lblFlygningar_1);
	}
}

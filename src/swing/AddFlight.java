package swing;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import app.Database;
import app.Flight;

public class AddFlight {

	private JFrame frame;
	public Database DB = new Database();
	private static ArrayList<Flight> flightlist = new ArrayList<Flight>();
	private Flight flight;
	private DefaultListModel listModel = new DefaultListModel();
	private List<Flight> flights = new ArrayList<Flight>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFlight window = new AddFlight();
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
	public AddFlight() {
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
		comboBox.setBounds(94, 60, 171, 24);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		flightlist = (ArrayList<Flight>) DB.getAllFlights();
		for (int i = 0; i < flightlist.size(); i++) {
			comboBox_1.addItem(flightlist.get(i).id);
		}
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setBounds(94, 109, 171, 24);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblSkapaNyFlygning = new JLabel("Skapa ny flygning med byten");
		lblSkapaNyFlygning.setBounds(24, 12, 205, 24);
		frame.getContentPane().add(lblSkapaNyFlygning);
		
		JLabel lblRutt = new JLabel("Rutt 1");
		lblRutt.setBounds(24, 65, 70, 15);
		frame.getContentPane().add(lblRutt);
		
		JLabel lblRutt_1 = new JLabel("Rutt 2");
		lblRutt_1.setBounds(24, 114, 70, 15);
		frame.getContentPane().add(lblRutt_1);
		
		JButton btnSkapa = new JButton("Skapa");
		btnSkapa.setBounds(37, 202, 117, 25);
		frame.getContentPane().add(btnSkapa);
	}
}

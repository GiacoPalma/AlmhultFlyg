package swing;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import app.Database;
import app.Flight;
import app.Route;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFlight {

	private JFrame frame;
	public Database DB = new Database();
	private static ArrayList<Route> flightlist = new ArrayList<Route>();
	private Flight flight;
	private Route route;
	private DefaultListModel listModel = new DefaultListModel();
	private List<Flight> flights = new ArrayList<Flight>();
	private List<Flight> flights2 = new ArrayList<Flight>();
	private List<String> list = new ArrayList<String>();
	private int selected1;
	private int selected2;
	
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
		
		final JComboBox comboBox = new JComboBox();
		
		flightlist = (ArrayList<Route>) DB.getAllRoutes();
		for (int i = 0; i < flightlist.size(); i++) {
			comboBox.addItem(flightlist.get(i).airport.getName()+" -> "+flightlist.get(i).dest_airport.getName());
		}
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(94, 60, 312, 24);
		frame.getContentPane().add(comboBox);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected2 = comboBox_1.getSelectedIndex();
				System.out.println(selected2);
			}
		});
		//flightlist = (ArrayList<Route>) DB.getAllRoutes();

		
		comboBox_1.setSelectedIndex(-1);
		comboBox_1.setBounds(94, 109, 312, 24);
		frame.getContentPane().add(comboBox_1);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedIndex() > 0){
					selected1 = comboBox.getSelectedIndex();
					comboBox_1.removeAllItems();
					for (int i = 0; i < flightlist.size(); i++) {
						if(flightlist.get(i).depature_airport_id == flightlist.get(selected1).destination_airport_id){
							comboBox_1.addItem(flightlist.get(i).airport.getName()+" -> "+flightlist.get(i).dest_airport.getName());
							list.add(""+flightlist.get(i).id);
						}
					} 
					
				}
			}
		});
		
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
		btnSkapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select1 = flightlist.get(selected1).id;
				int select2 = Integer.parseInt(list.get(selected2));
				comboBox_1.getSelectedItem();
				DB.addFlight(select1, select2);
				System.out.println("Rutt1_id:" + select1 + " <--> Rutt2_id:" + list.get(selected2));
				JOptionPane.showMessageDialog(frame, "Flygningen skapades!", "Success!",
				        JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnSkapa.setBounds(37, 202, 117, 25);
		frame.getContentPane().add(btnSkapa);
	}
}

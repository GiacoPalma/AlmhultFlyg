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
import app.Route;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class EditFlight {

	public Database DB = new Database();
	private static ArrayList<Route> flightlist = new ArrayList<Route>();
	private DefaultListModel listModel = new DefaultListModel();
	private List<Flight> flights = new ArrayList<Flight>();
	private Flight flight;
	private String inputDeptDateFormated = null;
	private List<String> routelist2 = new ArrayList<String>();
	private List<Route> RouteID = new ArrayList<Route>();

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

		final JComboBox comboBox = new JComboBox();
		flightlist = (ArrayList<Route>) DB.getAllRoutes();
		RouteID = (ArrayList<Route>) DB.getAllFlightID();
		
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(64, 217, 259, 25);
		frame.getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 48, 424, 120);
		frame.getContentPane().add(scrollPane);

		final JList list = new JList(listModel);
		for (int i = 0; i < RouteID.size(); i++) { 
			listModel.addElement(RouteID.get(i).airport.getName()+" -> "+RouteID.get(i).middle+" -> "+RouteID.get(i).dest_airport.getName());
			//Sam was here
		}
		scrollPane.setViewportView(list);

		JLabel lblFlygningar = new JLabel("Redigera flygningar med byten");
		lblFlygningar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFlygningar.setBounds(37, 11, 286, 25);
		frame.getContentPane().add(lblFlygningar);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(64, 180, 259, 25);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblRutt = new JLabel("Rutt 1");
		lblRutt.setBounds(12, 180, 70, 15);
		frame.getContentPane().add(lblRutt);
		
		JLabel lblRutt_1 = new JLabel("Rutt 2");
		lblRutt_1.setBounds(12, 217, 70, 15);
		frame.getContentPane().add(lblRutt_1);
		
		JButton btnUppdatera = new JButton("Uppdatera");
		btnUppdatera.setBounds(319, 180, 117, 25);
		frame.getContentPane().add(btnUppdatera);
		
		JButton btnTaBort = new JButton("Ta bort");
		btnTaBort.setBounds(319, 217, 117, 25);
		frame.getContentPane().add(btnTaBort);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int listselection = list.getSelectedIndex();
				comboBox.removeAllItems();
				comboBox_1.removeAllItems();
				comboBox_1.addItem(flightlist.get(listselection).airport.getName()+" -> "+flightlist.get(listselection).dest_airport.getName());
				for(int i = 0; i < RouteID.size(); i++) {
					System.out.println(RouteID.get(i).route2_id+" -- "+RouteID.get(listselection).id);
					if(RouteID.get(i).route2_id == RouteID.get(listselection).id){
						comboBox.addItem(flightlist.get(i).airport.getName()+" -> "+flightlist.get(i).dest_airport.getName());
						//routelist2.add(""+flightlist.get(i).id);
					}
				} 
			} 
		});
	}
}
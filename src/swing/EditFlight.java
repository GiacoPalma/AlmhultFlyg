package swing;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EditFlight {

	public Database DB = new Database();
	private static ArrayList<Route> flightlist = new ArrayList<Route>();
	private DefaultListModel listModel = new DefaultListModel();
	private List<Flight> flights = new ArrayList<Flight>();
	private List<Route> flightid = new ArrayList<Route>();
	private List<Route> allRoutes = new ArrayList<Route>();
	private List allRouteID = new ArrayList();//

	private Flight flight;
	private String inputDeptDateFormated = null;
	private String Routelist;
	private List<String> routelist2 = new ArrayList<String>();
	public int listselection;


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
		flightlist = (ArrayList<Route>) DB.getFlightsWithTwoRoutes();
		flightid = (List<Route>) DB.getAllFlightID();
		allRoutes = (List<Route>) DB.getAllRoutes(); //getAllRoutes
		for(int i = 0; i < DB.getAllRoutes().size(); i++){
			allRouteID.add(i, DB.getAllRoutes().get(i).id);
		}
		
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(64, 217, 259, 25);
		frame.getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 48, 424, 120);
		frame.getContentPane().add(scrollPane);

		final JList list = new JList(listModel);
		
		for (int i = 0; i < flightlist.size(); i++) { 
			listModel.addElement(flightlist.get(i).airport.getName()+" -> "+flightlist.get(i).middle+" -> "+flightlist.get(i).dest_airport.getName());
			//System.out.println(RouteID.get(i).id);
			}
		scrollPane.setViewportView(list);

		JLabel lblFlygningar = new JLabel("Redigera flygningar med byten");
		lblFlygningar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFlygningar.setBounds(37, 11, 286, 25);
		frame.getContentPane().add(lblFlygningar);
		final JComboBox comboBox_1 = new JComboBox();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				listselection = list.getSelectedIndex();
				routelist2.clear();
				comboBox_1.removeAllItems();
				comboBox.removeAllItems();
				comboBox_1.addItem(flightlist.get(listselection).airport.getName()+" -> "+flightlist.get(listselection).middle);
				for(int i = 0; i < allRouteID.size(); i++) {	
						if(allRouteID.get(i).equals(flightlist.get(listselection).route2_id)){//allRoutes.get(i).id == flightlist.get(listselection).id){
							//comboBox.addItem(flightlist.get(i).middle+" -> "+flightlist.get(i).dest_airport.getName());
							comboBox.addItem(allRoutes.get(i).airport.getName()+" -> "+allRoutes.get(i).dest_airport.getName());	
							Routelist = allRoutes.get(i).airport.getName()+" -> "+allRoutes.get(i).dest_airport.getName();
						}
				}
				for(int i = 0; i < allRoutes.size(); i++) {
					if(allRoutes.get(i).depature_airport_id == flightlist.get(listselection).middle_id){
						String routelist3 = allRoutes.get(i).airport.getName()+" -> "+allRoutes.get(i).dest_airport.getName();
							if(!routelist3.equals(Routelist)) {
								comboBox.addItem(allRoutes.get(i).airport.getName()+" -> "+allRoutes.get(i).dest_airport.getName());
								routelist2.add(""+allRoutes.get(i).id);
								//System.out.println(allRoutes.get(i).id);
						}
					}
				}
			}
		});
		comboBox_1.setBounds(64, 180, 259, 25);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblRutt = new JLabel("Rutt 1");
		lblRutt.setBounds(12, 180, 70, 15);
		frame.getContentPane().add(lblRutt);
		
		JLabel lblRutt_1 = new JLabel("Rutt 2");
		lblRutt_1.setBounds(12, 217, 70, 15);
		frame.getContentPane().add(lblRutt_1);
		
		JButton btnUppdatera = new JButton("Uppdatera");
		btnUppdatera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int listselection1 = comboBox.getSelectedIndex();
				int route2_id;
				if(listselection1 > 0) {
					route2_id = Integer.parseInt(routelist2.get(listselection1-1));
				} else {
					route2_id = Integer.parseInt(routelist2.get(listselection1));
				}
				System.out.println(flightlist.get(listselection).flight_id+" -> "+flightlist.get(listselection).id+" -> "+route2_id);
				String ret = DB.UpdateFlight(flightlist.get(listselection).flight_id, flightlist.get(listselection).route1_id, route2_id);
				JOptionPane.showMessageDialog(null, ret);
				EditFlight refresh = new EditFlight();
				EditFlight.this.frame.dispose();
				refresh.frame.setVisible(true);
			}
		});
		btnUppdatera.setBounds(319, 180, 117, 25);
		frame.getContentPane().add(btnUppdatera);
		
		JButton btnTaBort = new JButton("Ta bort");
		btnTaBort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ret = DB.RemoveFlights(flightlist.get(listselection).flight_id);
				JOptionPane.showMessageDialog(null, ret);
				EditFlight refresh = new EditFlight();
				EditFlight.this.frame.dispose();
				refresh.frame.setVisible(true);
			}
		});
		btnTaBort.setBounds(319, 217, 117, 25);
		frame.getContentPane().add(btnTaBort);
	}
}
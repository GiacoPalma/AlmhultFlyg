package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.JButton;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.toedter.calendar.JCalendar;
import java.awt.GridLayout;

import app.Airplane;
import app.Airport;
import app.Booking;
import app.Database;
import app.Flight;
import app.Route;
import app.User;

import javax.swing.JList;

import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;


public class BookSwing extends JFrame {

	public Database DB = new Database();
	private JPanel contentPane;
	private static ArrayList<Airport> airportlist = new ArrayList<Airport>();
	private DefaultListModel listModel = new DefaultListModel();
	private List<Flight> flights = new ArrayList<Flight>();
	private Flight flight;
	private int dest_id = 0;
	private int dep_id = 0;
	private String inputDeptDateFormated = null;
	private List<Flight> availableFlights = new ArrayList<Flight>();
	private String available="";
	private JPanel panel;
	private static BookSwing frame1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSwing frame = new BookSwing(null);
					frame.setVisible(true);
					frame1 = frame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookSwing(final User user) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 900, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		airportlist = (ArrayList<Airport>) DB.getAllAirports();

		final JComboBox combobox = new JComboBox();
		combobox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = combobox.getSelectedIndex();

			}
		});
		airportlist = (ArrayList<Airport>) DB.getAllAirports();
		for (int i = 0; i < airportlist.size(); i++) {
			combobox.addItem(airportlist.get(i).city);
		}
		combobox.setSelectedIndex(-1);

		combobox.setBounds(10, 25, 185, 20);
		contentPane.add(combobox);

		JLabel lblNewLabel = new JLabel("Avreseort");
		lblNewLabel.setBounds(10, 11, 115, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Destination");
		lblNewLabel_1.setBounds(10, 51, 67, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tillg\u00E4ngliga Flighter");
		lblNewLabel_2.setBounds(233, 11, 144, 14);
		contentPane.add(lblNewLabel_2);

		final JComboBox comboBox_1 = new JComboBox();
		airportlist = (ArrayList<Airport>) DB.getAllAirports();
		for (int i = 0; i < airportlist.size(); i++) {
			comboBox_1.addItem(airportlist.get(i).city);
		}
		comboBox_1.setSelectedIndex(-1);

		comboBox_1.setBounds(10, 66, 185, 20);
		contentPane.add(comboBox_1);

		

		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(10, 146, 185, 20);
		dateChooser.setVisible(false);
		contentPane.add(dateChooser);

		final JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(10, 197, 185, 20);
		dateChooser_1.setVisible(false);
		contentPane.add(dateChooser_1);

		JLabel lblNewLabel_3 = new JLabel("Pris:");
		lblNewLabel_3.setBounds(233, 203, 46, 14);
		contentPane.add(lblNewLabel_3);

		JButton btnSk = new JButton("S\u00F6k");

		btnSk.setBounds(109, 228, 89, 23);
		contentPane.add(btnSk);

		JRadioButton rdbtnEnkel = new JRadioButton("Enkel");
		rdbtnEnkel.setBounds(10, 93, 77, 23);
		contentPane.add(rdbtnEnkel);

		JRadioButton rdbtnTurRetur = new JRadioButton("Tur & Retur");
		rdbtnTurRetur.setBounds(89, 93, 109, 23);
		contentPane.add(rdbtnTurRetur);

		final JLabel lblterresa = new JLabel("\u00C5terresa");
		lblterresa.setBounds(10, 177, 89, 14);
		lblterresa.setVisible(false);
		contentPane.add(lblterresa);

		final JLabel lblUtresa = new JLabel("Utresa");
		lblUtresa.setBounds(10, 123, 46, 14);
		lblUtresa.setVisible(false);
		contentPane.add(lblUtresa);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(233, 35, 625, 70);
		contentPane.add(scrollPane);
		

		final JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		GridLayout ScrollLayout = new GridLayout(0,1);
		panel_1.setLayout(ScrollLayout);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnEnkel);
		group.add(rdbtnTurRetur);

		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserMenu reload = new UserMenu(user);
				dispose();
				reload.setVisible(true);
			}
		});

		btnSk.setBounds(109, 228, 89, 23);

		btnSk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Route route = new Route();
				Flight flight = new Flight(route);
				Booking booking = new Booking();
				panel_1.removeAll();
				panel_1.setSize(625, 70);
				int selDep = combobox.getSelectedIndex();
				try {
					dep_id = airportlist.get(selDep).id;
					flight.route1.setDepature_airport_id(dep_id);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					int selDest = comboBox_1.getSelectedIndex();
					dest_id = airportlist.get(selDest).id;
					flight.route1.setDestination_airport_id(dest_id);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					inputDeptDateFormated = null;
					if (dateChooser.getDate() != null) {
						java.util.Date depDate = dateChooser.getDate();
						inputDeptDateFormated = new SimpleDateFormat(
								"yyyy-MM-dd").format(depDate);
					}
					flight.route1.setDepature_date(inputDeptDateFormated);

				} catch (NullPointerException e) {

				}
				System.out.println("depid"+dep_id);
				System.out.println("destid"+dest_id);
				flights = Database.getAvailableFlights2(dep_id, dest_id,
						inputDeptDateFormated);
				
				if (!(flights.size() == 0)) {
					

					for (int i = 0; i < flights.size(); i++) {
					
						Airport airportRoute2 = new Airport();
						Airport destAirportRoute2 = new Airport();
						Airplane airplaneRoute2 = new Airplane();
						Airplane airplane = new Airplane();
						airplane = Database.getAirplane(flights.get(i).route1.airplane);
						System.out.println("flygplansmodell"+flights.get(i).route1.seats_booked);
						Airport airport = new Airport();
						Airport destAirport = new Airport();
						airport = Database.getAirport(flights.get(i).route1.depature_airport_id);
						destAirport = Database.getAirport(flights.get(i).route1.destination_airport_id);
						if(flights.get(i).checkRoute2()){
							airplaneRoute2 = Database.getAirplane(flights.get(i).route2.airplane);
							airportRoute2 = Database.getAirport(flights.get(i).route2.depature_airport_id);
							destAirportRoute2 = Database.getAirport(flights.get(i).route2.destination_airport_id);
						}
						
						System.out.println(flights.get(i).route1.airplane);
						System.out.println(flights.get(i).route2);
						
							if(booking.checkAvailability(airplane.getSeatsTotal(), flights.get(i).route1.seats_booked)){
								available = "";
								flights.get(i).route1.available = true;
								if(flights.get(i).checkRoute2()){
									if(booking.checkAvailability(airplane.getSeatsTotal(),flights.get(i).route2.seats_booked)){
										flights.get(i).route2.available = true;
									}else{
										available = "fullbokad";
										flights.get(i).route2.available = false;
									}
								}
								
							} else {
								available = "Fullbokad";
								flights.get(i).route1.available = false;
							}
							if(flights.get(i).checkRoute2()){
								
								String price = flights.get(i).route1.price.toString();
								String priceR2 = flights.get(i).route2.price.toString();
								listResults panel = new listResults();
								JPanel panel2 = new JPanel();
								panel2 = panel.listResults(BookSwing.this,flights, airport.getName(), destAirport.getName(), destAirportRoute2.getName(), airportRoute2.getName(), flights.get(i).route1.depature_date, flights.get(i).route1.destination_date, flights.get(i).route2.depature_date, flights.get(i).route2.destination_date, priceR2, price, i, user);
								
								Dimension maximum = new Dimension(625, 200);
								scrollPane.setMaximumSize(maximum);

								
								panel_1.setSize(panel_1.getWidth(), panel_1.getHeight()+panel2.getHeight());
								if(scrollPane.getHeight() <= maximum.getHeight()){
									scrollPane.setSize(panel_1.getWidth(), panel_1.getHeight());
								}
								panel_1.add(panel2);
								
								
							}else{
								String price = flights.get(i).route1.price.toString();
								String priceR2 = "";
								listResults panel = new listResults();
								JPanel panel2 = new JPanel();
								panel2 = panel.listResults(BookSwing.this, flights, airport.getName(), destAirport.getName(), "", "", flights.get(i).route1.depature_date, flights.get(i).route1.destination_date, "", "", "", price, i, user);
								Dimension maximum = new Dimension(625, 200);
								scrollPane.setMaximumSize(maximum);
								panel_1.setSize(panel_1.getWidth(), panel_1.getHeight()+panel2.getHeight());
								if(scrollPane.getHeight() <= maximum.getHeight()){
									scrollPane.setSize(panel_1.getWidth(), panel_1.getHeight());
								}

								panel_1.add(panel2);
								
							}
				}
					}else {
				
				}
			}
		});
		btnSk.setBounds(106, 228, 89, 23);

		contentPane.add(btnSk);

		btnTillbaka.setBounds(7, 228, 89, 23);
		contentPane.add(btnTillbaka);
		
		
		
		
		

		ActionListener rdbtnEnkelListner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				dateChooser.setVisible(true);
				dateChooser_1.setVisible(false);
				lblUtresa.setVisible(true);
				lblterresa.setVisible(false);
			}

		};
		rdbtnEnkel.addActionListener(rdbtnEnkelListner);

		ActionListener rdbtnTurReturListner = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				dateChooser.setVisible(true);
				dateChooser_1.setVisible(true);
				lblUtresa.setVisible(true);
				lblterresa.setVisible(true);
			}

		};
		rdbtnTurRetur.addActionListener(rdbtnTurReturListner);

	}
	public JPanel getPanel() {
		return panel;
	}
}

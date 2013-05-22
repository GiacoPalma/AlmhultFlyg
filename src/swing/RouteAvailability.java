package swing;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class RouteAvailability extends JFrame {

	public Database DB = new Database();
	private JPanel contentPane;
	private static ArrayList<Airport> airportlist = new ArrayList<Airport>();
	private JList list;
	private DefaultListModel listModel = new DefaultListModel();
	private List<Flight> flights = new ArrayList<Flight>();
	private Flight flight;
	private int dest_id = 0;
	private int dep_id = 0;
	private String inputDeptDateFormated = null;
	private List<Flight> availableFlights = new ArrayList<Flight>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RouteAvailability frame = new RouteAvailability();
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
	public RouteAvailability() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1100, 300);
		contentPane = new JPanel();
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

		combobox.setBounds(10, 33, 185, 20);
		contentPane.add(combobox);

		JLabel lblNewLabel = new JLabel("Avreseort");
		lblNewLabel.setBounds(10, 11, 115, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Destination");
		lblNewLabel_1.setBounds(11, 65, 67, 14);
		contentPane.add(lblNewLabel_1);

		list = new JList(listModel);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		list.setBounds(233, 28, 848, 163);
		contentPane.add(list);

		JLabel lblNewLabel_2 = new JLabel("Tillg\u00E4ngliga Flighter");
		lblNewLabel_2.setBounds(233, 11, 144, 14);
		contentPane.add(lblNewLabel_2);

		final JComboBox comboBox_1 = new JComboBox();
		airportlist = (ArrayList<Airport>) DB.getAllAirports();
		for (int i = 0; i < airportlist.size(); i++) {
			comboBox_1.addItem(airportlist.get(i).city);
		}
		comboBox_1.setSelectedIndex(-1);

		comboBox_1.setBounds(10, 91, 185, 20);
		contentPane.add(comboBox_1);

		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(10, 146, 185, 20);
		contentPane.add(dateChooser);

		JButton btnSk = new JButton("S\u00F6k");

		btnSk.setBounds(109, 228, 89, 23);
		contentPane.add(btnSk);

		final JLabel lblUtresa = new JLabel("Datum");
		lblUtresa.setBounds(10, 123, 46, 14);
		lblUtresa.setVisible(false);
		contentPane.add(lblUtresa);

		ButtonGroup group = new ButtonGroup();

		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminMenu reload = new AdminMenu();
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

				flights = Database.getAvailableFlights(dep_id, dest_id,
						inputDeptDateFormated);
				if (!(flights.size() == 0)) {
					listModel.clear();

					for (int i = 0; i < flights.size(); i++) {
						Airport airport = new Airport();
						Airport destAirport = new Airport();
						airport = Database.getAirport(flights.get(i).route1.depature_airport_id);
						destAirport = Database.getAirport(flights.get(i).route1.destination_airport_id);
						Airplane airplane = new Airplane();
						airplane = Database.getAirplane(flights.get(i).route1.airplane);

						int available = booking.getAvailability(airplane.getSeatsTotal(),
								flights.get(i).route1.getSeats_booked());
						listModel.addElement(airport.getName() + " - "
								+ destAirport.getName() + " "
								+ flights.get(i).route1.getDepature_date()
								+ " - "
								+ flights.get(i).route1.getDestination_date()
								+ " Pris: " + flights.get(i).route1.getPrice()
								+ " Platser lediga: "
								+ available);
					}
				} else {
					listModel.clear();
					listModel.addElement("Det finns inga flygningar");
				}
			}
		});
		btnSk.setBounds(106, 228, 89, 23);

		contentPane.add(btnSk);

		btnTillbaka.setBounds(7, 228, 89, 23);
		contentPane.add(btnTillbaka);

	}
}
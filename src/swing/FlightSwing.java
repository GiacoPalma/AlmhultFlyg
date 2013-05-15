package swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SpinnerDateModel;

import app.Airplane;
import app.Airport;
import app.Database;
import app.Flight;
import app.Route;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.UIManager;

import org.apache.commons.lang3.StringUtils;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.toedter.calendar.JCalendar;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import com.toedter.calendar.JDateChooser;

import javax.swing.JFormattedTextField;
import javax.swing.JList;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.JTextPane;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class FlightSwing extends JFrame {

	private JPanel contentPane;
	public Database database = new Database();
	private JTextField textField;
	public int dep;
	public int dest;
	public Route route = new Route();;
	final List<Airplane> airplanes = database.getAllAirplanes();
	private JButton btnHmtaPris;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightSwing frame = new FlightSwing();
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
	public FlightSwing() {
		setTitle("L�gg till flyg");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dest = comboBox_1.getSelectedIndex();
				if(dep >= 0 && dest >= 0){
					
				}
			}
		});
		comboBox_1.setBounds(327, 90, 197, 27);
		contentPane.add(comboBox_1);

		JLabel lblTill = new JLabel("Till:");
		lblTill.setBounds(287, 95, 24, 16);
		contentPane.add(lblTill);

		JLabel lblPris = new JLabel("Pris:");
		lblPris.setBounds(23, 212, 27, 16);
		contentPane.add(lblPris);

		JLabel lblFrn = new JLabel("Fr\u00E5n: ");
		lblFrn.setBounds(15, 95, 35, 16);
		contentPane.add(lblFrn);

		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dep = comboBox.getSelectedIndex();
			}
		});
		comboBox.setBounds(68, 90, 197, 27);
		contentPane.add(comboBox);

		JButton btnLggTill = new JButton("L\u00E4gg till");
		btnLggTill.setBounds(375, 206, 95, 29);
		contentPane.add(btnLggTill);

		JLabel lblLggTillFlygning = new JLabel("L\u00E4gg till flygning");
		lblLggTillFlygning.setBounds(44, 31, 196, 37);
		lblLggTillFlygning.setFont(new Font("Helvetica", Font.BOLD, 24));
		contentPane.add(lblLggTillFlygning);

		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(68, 128, 197, 28);
		contentPane.add(dateChooser);

		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(10, 140, 61, 16);
		contentPane.add(lblDatum);

		final JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(327, 128, 197, 28);
		contentPane.add(dateChooser_1);

		JLabel lblDatum_1 = new JLabel("Datum:");
		lblDatum_1.setBounds(275, 140, 35, 16);
		contentPane.add(lblDatum_1);

		textField = new JTextField();
		textField.setBounds(68, 206, 197, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		final JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner,
				"HH:mm");
		timeSpinner.setBounds(68, 167, 197, 28);
		timeSpinner.setEditor(timeEditor);
		contentPane.add(timeSpinner);

		final JSpinner timeSpinner_1 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor_1 = new JSpinner.DateEditor(
				timeSpinner_1, "HH:mm");
		timeSpinner_1.setBounds(327, 167, 197, 28);
		timeSpinner_1.setEditor(timeEditor_1);
		contentPane.add(timeSpinner_1);

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date noonTime;
		try {
			noonTime = sdf.parse("12:00:00");
			timeSpinner.setValue(noonTime);
			timeSpinner_1.setValue(noonTime);
		} catch (java.text.ParseException e1) {
			e1.printStackTrace();
		}

		final List<Airport> airports = database.getAllAirports();

		for (int i = 0; i < airports.size(); i++) {
			comboBox.addItem(airports.get(i).getCity() + " - "
					+ airports.get(i).getName());
		}
		comboBox.setSelectedIndex(-1);

		for (int i = 0; i < airports.size(); i++) {
			comboBox_1.addItem(airports.get(i).getCity() + " - "
					+ airports.get(i).getName());
		}
		comboBox_1.setSelectedIndex(-1);

		JButton btnRensaFlten = new JButton("Rensa f\u00E4lten");
		btnRensaFlten.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				comboBox.setSelectedIndex(-1);
				comboBox_1.setSelectedIndex(-1);
				dateChooser.setDate(null);
				dateChooser_1.setDate(null);
				textField.setText(null);
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				Date noonTime;
				try {
					noonTime = sdf.parse("12:00:00");
					timeSpinner.setValue(noonTime);
					timeSpinner_1.setValue(noonTime);
				} catch (java.text.ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRensaFlten.setBounds(364, 246, 117, 29);
		contentPane.add(btnRensaFlten);

		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminMenu AdminMenu = new AdminMenu();
				AdminMenu.setVisible(true);
				setVisible(false);
			}
		});
		btnTillbaka.setBounds(364, 286, 117, 29);
		contentPane.add(btnTillbaka);

		JLabel lblTid = new JLabel("Tid:");
		lblTid.setBounds(26, 173, 24, 16);
		contentPane.add(lblTid);

		JLabel lblTid_1 = new JLabel("Tid:");
		lblTid_1.setBounds(287, 173, 24, 16);
		contentPane.add(lblTid_1);
		
		final JComboBox comboBox_airplane = new JComboBox();
		comboBox_airplane.setBounds(68, 246, 197, 28);
		contentPane.add(comboBox_airplane);
		
		for (int i = 0; i < airplanes.size();i++){
			comboBox_airplane.addItem(airplanes.get(i).model);
		}
		
		
		JLabel lblFlygplan = new JLabel("Flygplan:");
		lblFlygplan.setBounds(10, 253, 46, 14);
		contentPane.add(lblFlygplan);
		
		JLabel lblKronor = new JLabel("Kronor");
		lblKronor.setBounds(269, 213, 46, 14);
		contentPane.add(lblKronor);
		
		JLabel lblAvstnd = new JLabel("Avst\u00E5nd:");
		lblAvstnd.setBounds(10, 293, 61, 14);
		contentPane.add(lblAvstnd);
		
		JLabel lblKilometer = new JLabel(" Kilometer");
		lblKilometer.setBounds(269, 293, 61, 14);
		contentPane.add(lblKilometer);
		
		
		final JFormattedTextField textFieldDistance = new JFormattedTextField();
		textFieldDistance.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String test = textFieldDistance.getText();
				if (!test.equals("")) {
					int distance = Integer.parseInt(textFieldDistance.getText());
					int airplane_id = comboBox_airplane.getSelectedIndex();
					System.out.println("test"+textFieldDistance.getText());
					//route.setPrice(distance, airplanes.get(airplane_id).fuel_per_km, airplanes.get(airplane_id).seats_total);
					textField.setText(""+route.price);
				}
			}
		});
		textFieldDistance.setBounds(68, 286, 197, 29); 
		contentPane.add(textFieldDistance);
		
		
		
		btnHmtaPris = new JButton("Hämta pris");
		
		btnHmtaPris.setBounds(364, 327, 117, 25);
		btnHmtaPris.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int distance = Integer.parseInt(textFieldDistance.getText());
					int airplane_id = comboBox_airplane.getSelectedIndex();
					System.out.println("test"+textFieldDistance.getText());
					route.setPrice(distance, airplanes.get(airplane_id).fuel_per_km, airplanes.get(airplane_id).seats_total);
					textField.setText(""+route.price);
				
			}
		});
		contentPane.add(btnHmtaPris);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblFrn, lblDatum, dateChooser, lblTid, timeSpinner, lblPris, textField, dateChooser.getCalendarButton(), lblTill, comboBox_1, lblDatum_1, dateChooser_1, timeSpinner_1, lblTid_1, btnRensaFlten, btnLggTill, btnTillbaka, contentPane, dateChooser_1.getCalendarButton(), comboBox, lblLggTillFlygning}));
		
		
		btnLggTill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Route route = new Route();
				if (comboBox.getSelectedIndex() == -1) {
					route.setDepature_airport_id(0);
				} else {
					int deptId = comboBox.getSelectedIndex();
					route.setDepature_airport_id(airports.get(deptId).getId());
				}
				if (comboBox_1.getSelectedIndex() == -1) {
					route.setDestination_airport_id(0);
				} else {
					int destId = comboBox_1.getSelectedIndex();
					route.setDestination_airport_id(airports.get(destId)
							.getId());
				}
				if (comboBox_airplane.getSelectedIndex() == -1){
					route.airplane = 0;
				} else{
					int airplane_id = comboBox_airplane.getSelectedIndex();
					route.airplane = airplane_id;
				}
				try {
					Date inputDeptDate = dateChooser.getDate();
					String inputDeptDateFormated = new SimpleDateFormat(
							"yyyy-MM-dd").format(inputDeptDate);
					SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
					Date time = (Date) timeSpinner.getValue();
					String formattedDate = format.format(time);
					route.setDepature_date(inputDeptDateFormated + " "
							+ formattedDate);
					Date inputDestDate = dateChooser_1.getDate();
					String inputDestDateFormated = new SimpleDateFormat(
							"yyyy-MM-dd").format(inputDestDate);
					Date time2 = (Date) timeSpinner_1.getValue();
					String formattedDate2 = format.format(time2);
					route.setDestination_date(inputDestDateFormated + " "
							+ formattedDate2);
				} catch (NullPointerException e) {

				}
				try {
					int inputPrice = Integer.parseInt(textField.getText());
					route.price = inputPrice;
				} catch (NumberFormatException e) {

				}
				try { 
					int inputDistance = Integer.parseInt(textFieldDistance.getText());
					route.distance = inputDistance;
				} catch(NumberFormatException e){
					
				}
				if (route.validate()) { 
					boolean created = database.AddRoute(route.depature_airport_id, route.depature_date, route.destination_airport_id, route.destination_date, route.price, route.airplane, route.distance);
					if (created) {
						JOptionPane.showMessageDialog(new JFrame(),
								"Flygningen har lagts till", "Dialog",
								JOptionPane.INFORMATION_MESSAGE);
						comboBox.setSelectedIndex(-1);
						comboBox_1.setSelectedIndex(-1);
						dateChooser.setDate(null);
						dateChooser_1.setDate(null);
						textField.setText(null);
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						Date noonTime;
						try {
							noonTime = sdf.parse("12:00:00");
							timeSpinner.setValue(noonTime);
							timeSpinner_1.setValue(noonTime);
						} catch (java.text.ParseException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(new JFrame(),
								"N�gonting gick fel", "Dialog",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					String output = StringUtils.join(
							route.errorMessages.toArray(), "\n");
					JOptionPane.showMessageDialog(new JFrame(), output,
							"Dialog", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}
	public JTextField getTextFieldDistance() {
		return getTextFieldDistance();
	}
	public JButton getBtnHmtaPris() {
		return btnHmtaPris;
	}
}

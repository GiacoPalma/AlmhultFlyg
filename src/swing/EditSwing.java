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

public class EditSwing extends JFrame {

	private JPanel contentPane;
	public Database database = new Database();
	private JTextField textField;
	private int selectedId;
	private JTextField textFieldDistance;

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

	public void setId(int selectedId) {
		this.selectedId = selectedId;
	}

	/**
	 * Create the frame.
	 */
	public EditSwing(int selectedId) {
		this.selectedId = selectedId;
		final Flight selectedFlight = database.getFlight(selectedId);
		setTitle("Lägg till flyg");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JComboBox comboBox_1 = new JComboBox();
		Airport dest_airport = database.getAirport(selectedFlight.route1.getDestination_airport_id());
		comboBox_1.setBounds(327, 107, 197, 27);
		contentPane.add(comboBox_1);

		JLabel lblTill = new JLabel("Till:");
		lblTill.setBounds(293, 118, 24, 16);
		contentPane.add(lblTill);

		JLabel lblPris = new JLabel("Pris:");
		lblPris.setBounds(23, 219, 27, 16);
		contentPane.add(lblPris);

		JLabel lblFrn = new JLabel("Fr\u00E5n: ");
		lblFrn.setBounds(15, 118, 35, 16);
		contentPane.add(lblFrn);
		Airport dep_airport = database.getAirport(selectedFlight.route1.getDepature_airport_id());
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(68, 108, 197, 27);
		contentPane.add(comboBox);

		final List<Airport> airports = database.getAllAirports();

		for (int i = 0; i < airports.size(); i++) {
			comboBox.addItem(airports.get(i).print());
		}

		for (int i = 0; i < airports.size(); i++) {
			comboBox_1.addItem(airports.get(i).print());

		}

		for (int i = 0; i < comboBox.getItemCount(); i++) {
			if (comboBox.getItemAt(i).equals(dep_airport.print())) {
				comboBox.setSelectedIndex(i);
				break;
			}
		}

		for (int i = 0; i < comboBox_1.getItemCount(); i++) {
			if (comboBox_1.getItemAt(i).equals(dest_airport.print())) {
				comboBox_1.setSelectedIndex(i);
				break;
			}
		}

		JButton btnLggTill = new JButton("Uppdatera");
		btnLggTill.setBounds(374, 234, 95, 29);
		contentPane.add(btnLggTill);

		JLabel lblLggTillFlygning = new JLabel("Redigera flygning");
		lblLggTillFlygning.setBounds(44, 40, 292, 25);
		lblLggTillFlygning.setFont(new Font("Helvetica", Font.BOLD, 24));
		contentPane.add(lblLggTillFlygning);

		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(68, 146, 197, 28);
		contentPane.add(dateChooser);
		dateChooser.setDate(selectedFlight.route1.getFormattedDepature_date());

		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(10, 158, 61, 16);
		contentPane.add(lblDatum);

		final JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(327, 145, 197, 28);
		contentPane.add(dateChooser_1);
		dateChooser_1.setDate(selectedFlight.route1.getFormattedDestination_date());

		JLabel lblDatum_1 = new JLabel("Datum:");
		lblDatum_1.setBounds(275, 158, 61, 16);
		contentPane.add(lblDatum_1);

		textField = new JTextField();
		textField.setBounds(68, 213, 197, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		String sendPrice = Integer.toString(selectedFlight.route1.price);

		textField.setText(sendPrice);

		final JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner,
				"HH:mm");
		timeSpinner.setBounds(68, 179, 197, 28);
		timeSpinner.setEditor(timeEditor);
		contentPane.add(timeSpinner);

		final JSpinner timeSpinner_1 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor_1 = new JSpinner.DateEditor(
				timeSpinner_1, "HH:mm");
		timeSpinner_1.setBounds(327, 179, 197, 28);
		timeSpinner_1.setEditor(timeEditor_1);
		contentPane.add(timeSpinner_1);

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date depTime = selectedFlight.route1.getFormattedDepature_date();
		Date destTime = selectedFlight.route1.getFormattedDestination_date();

		timeSpinner.setValue(depTime);
		timeSpinner_1.setValue(destTime);

		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DeleteEditFlight deleteEditFlight = new DeleteEditFlight();
				deleteEditFlight.setVisible(true);
				setVisible(false);
			}
		});
		btnTillbaka.setBounds(374, 275, 95, 29);
		contentPane.add(btnTillbaka);

		JLabel lblTid = new JLabel("Tid:");
		lblTid.setBounds(23, 185, 61, 16);
		contentPane.add(lblTid);

		JLabel lblTid_1 = new JLabel("Tid:");
		lblTid_1.setBounds(291, 185, 61, 16);
		contentPane.add(lblTid_1);
		
		final JComboBox comboBox_airplane = new JComboBox();
		comboBox_airplane.setBounds(68, 252, 197, 27);
		contentPane.add(comboBox_airplane);
		final List<Airplane> airplanes = database.getAllAirplanes();
		
		for(int i = 0; i<airplanes.size();i++){
			comboBox_airplane.addItem(airplanes.get(i).model);
		}
		comboBox_airplane.setSelectedIndex(selectedFlight.route1.airplane);
		
		JLabel lblFlygplan = new JLabel("Flygplan:");
		lblFlygplan.setBounds(10, 258, 46, 14);
		contentPane.add(lblFlygplan);
		
		JLabel lblKronor = new JLabel("Kronor");
		lblKronor.setBounds(271, 220, 46, 14);
		contentPane.add(lblKronor);
		
		textFieldDistance = new JTextField();
		textFieldDistance.setBounds(68, 290, 197, 27);
		contentPane.add(textFieldDistance);
		textFieldDistance.setColumns(10);
		
		JLabel lblAvstnd = new JLabel("Avst\u00E5nd:");
		lblAvstnd.setBounds(10, 296, 61, 14);
		contentPane.add(lblAvstnd);
		
		JLabel lblKilometer = new JLabel("Kilometer");
		lblKilometer.setBounds(271, 296, 46, 14);
		contentPane.add(lblKilometer);

		btnLggTill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Route route = new Route();
			
				route.id = selectedFlight.route1.id;
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
				}else{
					int airplane_id = comboBox_airplane.getSelectedIndex();
					route.airplane = airplanes.get(airplane_id).id;
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
				try{
					int inputDistance = Integer.parseInt(textFieldDistance.getText());
					route.distance = inputDistance;
				}catch(NumberFormatException e){
					
				}
				if (route.validate()) {
					boolean updated = database.UpdateRoute(route);
					if (updated) {
						JOptionPane.showMessageDialog(new JFrame(),
								"Flygningen har uppdaterats", "Dialog",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(new JFrame(),
								"NŒgonting gick fel", "Dialog",
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
}

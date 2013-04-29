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
import app.Airport;
import app.Database;
import app.Flight;
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

public class FlightSwing extends JFrame {

	private JPanel contentPane;
	public Database database = new Database();
	private JTextField textField;

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
		setTitle("Lägg till flyg");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(315, 108, 138, 27);
		contentPane.add(comboBox_1);

		JLabel lblTill = new JLabel("Till:");
		lblTill.setBounds(265, 112, 24, 16);
		contentPane.add(lblTill);

		JLabel lblPris = new JLabel("Pris:");
		lblPris.setBounds(44, 219, 27, 16);
		contentPane.add(lblPris);

		JLabel lblFrn = new JLabel("Fr\u00E5n: ");
		lblFrn.setBounds(44, 112, 35, 16);
		contentPane.add(lblFrn);

		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(95, 108, 138, 27);
		contentPane.add(comboBox);

		JButton btnLggTill = new JButton("L\u00E4gg till");
		btnLggTill.setBounds(44, 272, 95, 29);
		contentPane.add(btnLggTill);

		JLabel lblLggTillFlygning = new JLabel("L\u00E4gg till flygning");
		lblLggTillFlygning.setBounds(44, 40, 196, 25);
		lblLggTillFlygning.setFont(new Font("Helvetica", Font.BOLD, 24));
		contentPane.add(lblLggTillFlygning);

		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(95, 145, 138, 28);
		contentPane.add(dateChooser);

		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(44, 149, 61, 16);
		contentPane.add(lblDatum);

		final JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("yyyy-MM-dd");
		dateChooser_1.setBounds(315, 142, 138, 28);
		contentPane.add(dateChooser_1);

		JLabel lblDatum_1 = new JLabel("Datum:");
		lblDatum_1.setBounds(265, 148, 61, 16);
		contentPane.add(lblDatum_1);

		textField = new JTextField();
		textField.setBounds(95, 213, 138, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		final JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner,
				"HH:mm");
		timeSpinner.setBounds(95, 179, 138, 28);
		timeSpinner.setEditor(timeEditor);
		contentPane.add(timeSpinner);

		final JSpinner timeSpinner_1 = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor_1 = new JSpinner.DateEditor(
				timeSpinner_1, "HH:mm");
		timeSpinner_1.setBounds(315, 178, 138, 28);
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
			comboBox.addItem(airports.get(i).getName() + " - "
					+ airports.get(i).getCity());
		}
		comboBox.setSelectedIndex(-1);

		for (int i = 0; i < airports.size(); i++) {
			comboBox_1.addItem(airports.get(i).getName() + " - "
					+ airports.get(i).getCity());
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
		btnRensaFlten.setBounds(152, 272, 117, 29);
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
		btnTillbaka.setBounds(44, 318, 117, 29);
		contentPane.add(btnTillbaka);

		JLabel lblKr = new JLabel("kr");
		lblKr.setBounds(239, 219, 61, 16);
		contentPane.add(lblKr);

		JLabel lblTid = new JLabel("Tid:");
		lblTid.setBounds(44, 185, 61, 16);
		contentPane.add(lblTid);

		JLabel lblTid_1 = new JLabel("Tid:");
		lblTid_1.setBounds(266, 183, 61, 16);
		contentPane.add(lblTid_1);

		btnLggTill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Flight flight = new Flight();
				if (comboBox.getSelectedIndex() == -1) {
					flight.setDepature_airport_id(0);
				} else {
					int deptId = comboBox.getSelectedIndex();
					flight.setDepature_airport_id(airports.get(deptId).getId());
				}
				if (comboBox_1.getSelectedIndex() == -1) {
					flight.setDestination_airport_id(0);
				} else {
					int destId = comboBox_1.getSelectedIndex();
					flight.setDestination_airport_id(airports.get(destId)
							.getId());
				}
				try {
					Date inputDeptDate = dateChooser.getDate();
					String inputDeptDateFormated = new SimpleDateFormat(
							"yyyy-MM-dd").format(inputDeptDate);
					SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
					Date time = (Date) timeSpinner.getValue();
					String formattedDate = format.format(time);
					flight.setDepature_date(inputDeptDateFormated + " "
							+ formattedDate);
					Date inputDestDate = dateChooser_1.getDate();
					String inputDestDateFormated = new SimpleDateFormat(
							"yyyy-MM-dd").format(inputDestDate);
					Date time2 = (Date) timeSpinner_1.getValue();
					String formattedDate2 = format.format(time2);
					flight.setDestination_date(inputDestDateFormated + " "
							+ formattedDate2);
				} catch (NullPointerException e) {

				}
				try {
					int inputPrice = Integer.parseInt(textField.getText());
					flight.setPrice(inputPrice);
				} catch (NumberFormatException e) {

				}
				if (flight.validate()) {
					boolean created = database.createFlight(flight);
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
								"NŒgonting gick fel", "Dialog",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					String output = StringUtils.join(
							flight.errorMessages.toArray(), "\n");
					JOptionPane.showMessageDialog(new JFrame(), output,
							"Dialog", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}
}

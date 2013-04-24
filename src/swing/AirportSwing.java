package swing;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import app.Airport;
import app.Database;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class AirportSwing extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public Database DB = new Database();
	private JComboBox comboBox;
	private JButton btnLggTill;
	private JButton btnTaBort;
	private JButton btnRedigera;
	private static java.util.List <String> Airport_name = new ArrayList<String>();
	private static java.util.List <String> Airport_city = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AirportSwing frame = new AirportSwing();
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
	public AirportSwing() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
	    comboBox.setBounds(81, 31, 116, 27);
		
		contentPane.add(comboBox);
		List<Airport> airportlist = new ArrayList<Airport>();
		airportlist = DB.getAllAirports();
		comboBox.addItem("-- Välj --");
		for (int i = 0; i<airportlist.size();i++){
			comboBox.addItem(airportlist.get(i).name);
			Airport_name.add(airportlist.get(i).name);
			Airport_city.add(airportlist.get(i).city);
		}
		
		JLabel lblFlygplatser = new JLabel("Flygplatser:");
		lblFlygplatser.setBounds(6, 35, 75, 16);
		contentPane.add(lblFlygplatser);
		
		btnLggTill = new JButton("L\u00E4gg till");
		btnLggTill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddAirport air = new AddAirport();
				air.setVisible(true);
			}
		});
		btnLggTill.setBounds(80, 70, 117, 29);
		contentPane.add(btnLggTill);
		
		btnRedigera = new JButton("Uppdatera");
		btnRedigera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRedigera.setBounds(310, 120, 117, 29);
		contentPane.add(btnRedigera);
		
		btnTaBort = new JButton("Ta bort");
		btnTaBort.setBounds(310, 150, 117, 29);
		contentPane.add(btnTaBort);
		
		textField = new JTextField();
		textField.setBounds(310, 31, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(310, 69, 134, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int i = (comboBox.getSelectedIndex() - 1);
		    	textField.setText(Airport_name.get(i));
		    	textField_1.setText(Airport_city.get(i));
		    }
		});
		
		JLabel lblNamn = new JLabel("Namn:");
		lblNamn.setBounds(254, 35, 61, 16);
		contentPane.add(lblNamn);
		
		JLabel lblStad = new JLabel("Stad:");
		lblStad.setBounds(254, 76, 61, 16);
		contentPane.add(lblStad);
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public JButton getBtnLggTill() {
		return btnLggTill;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JButton getBtnTaBort() {
		return btnTaBort;
	}
	public JButton getBtnRedigera() {
		return btnRedigera;
	}
}

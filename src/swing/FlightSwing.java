package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FlightSwing extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		setTitle("Boka flyg");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(301, 82, 145, 27);
		contentPane.add(comboBox_1);
		
		JLabel lblFrnlmhult = new JLabel("Fr\u00E5n: \u00C4lmhult");
		lblFrnlmhult.setBounds(59, 86, 122, 16);
		contentPane.add(lblFrnlmhult);
		
		JLabel lblTill = new JLabel("Till:");
		lblTill.setBounds(244, 86, 61, 16);
		contentPane.add(lblTill);
		
		JLabel lblUtgendeFlyg = new JLabel("Utg\u00E5ende flyg");
		lblUtgendeFlyg.setBounds(59, 58, 145, 16);
		contentPane.add(lblUtgendeFlyg);
		
		JLabel lblInkommandeFlyg = new JLabel("Inkommande flyg");
		lblInkommandeFlyg.setBounds(59, 213, 122, 16);
		contentPane.add(lblInkommandeFlyg);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(54, 241, 133, 27);
		contentPane.add(comboBox);
		
		JLabel lblTilllmhult = new JLabel("Till: \u00C4lmhult");
		lblTilllmhult.setBounds(301, 245, 107, 16);
		contentPane.add(lblTilllmhult);
		
		textField = new JTextField();
		textField.setBounds(144, 121, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 161, 134, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAvgngstid = new JLabel("Avg\u00E5ngstid:");
		lblAvgngstid.setBounds(59, 127, 87, 16);
		contentPane.add(lblAvgngstid);
		
		JLabel lblPris = new JLabel("Pris:");
		lblPris.setBounds(59, 167, 61, 16);
		contentPane.add(lblPris);
		
		textField_2 = new JTextField();
		textField_2.setBounds(144, 280, 134, 28);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(144, 320, 134, 28);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAvgngstid_1 = new JLabel("Avg\u00E5ngstid:");
		lblAvgngstid_1.setBounds(59, 286, 82, 16);
		contentPane.add(lblAvgngstid_1);
		
		JLabel lblPris_1 = new JLabel("Pris:");
		lblPris_1.setBounds(59, 326, 61, 16);
		contentPane.add(lblPris_1);
		
		JButton btnUppdatera = new JButton("Uppdatera");
		btnUppdatera.setBounds(301, 162, 117, 29);
		contentPane.add(btnUppdatera);
		
		JButton btnUppdatera_1 = new JButton("Uppdatera");
		btnUppdatera_1.setBounds(301, 321, 117, 29);
		contentPane.add(btnUppdatera_1);
	}
}

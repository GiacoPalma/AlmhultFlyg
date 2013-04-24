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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(276, 82, 145, 27);
		contentPane.add(comboBox_1);
		
		JLabel lblTill = new JLabel("Till:");
		lblTill.setBounds(244, 86, 61, 16);
		contentPane.add(lblTill);
		
		JLabel lblUtgendeFlyg = new JLabel("Utg\u00E5ende flyg");
		lblUtgendeFlyg.setBounds(59, 58, 145, 16);
		contentPane.add(lblUtgendeFlyg);
		
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
		
		JButton btnUppdatera = new JButton("Uppdatera");
		btnUppdatera.setBounds(301, 162, 117, 29);
		contentPane.add(btnUppdatera);
		
		JLabel lblFrn = new JLabel("Fr\u00E5n: ");
		lblFrn.setBounds(59, 86, 61, 16);
		contentPane.add(lblFrn);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(95, 82, 126, 27);
		contentPane.add(comboBox);
	}
}

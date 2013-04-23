package app;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AirportSwing extends JFrame {

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(81, 31, 116, 27);
		contentPane.add(comboBox);
		
		JLabel lblFlygplatser = new JLabel("Flygplatser:");
		lblFlygplatser.setBounds(6, 35, 75, 16);
		contentPane.add(lblFlygplatser);
		
		JButton btnLggTill = new JButton("L\u00E4gg till");
		btnLggTill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddAirport air = new AddAirport();
				air.setVisible(true);
			}
		});
		btnLggTill.setBounds(80, 70, 117, 29);
		contentPane.add(btnLggTill);
		
		JButton btnRedigera = new JButton("Uppdatera");
		btnRedigera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRedigera.setBounds(310, 120, 117, 29);
		contentPane.add(btnRedigera);
		
		JButton btnTaBort = new JButton("Ta bort");
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
		
		JLabel lblNamn = new JLabel("Namn:");
		lblNamn.setBounds(254, 35, 61, 16);
		contentPane.add(lblNamn);
		
		JLabel lblStad = new JLabel("Stad:");
		lblStad.setBounds(254, 76, 61, 16);
		contentPane.add(lblStad);
	}
}
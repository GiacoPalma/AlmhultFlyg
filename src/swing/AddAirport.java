package swing;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import app.Database;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AddAirport extends JFrame {

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
					AddAirport frame = new AddAirport();
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
	public AddAirport() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(116, 52, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(116, 89, 134, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNamn = new JLabel("Namn:");
		lblNamn.setBounds(48, 58, 61, 16);
		contentPane.add(lblNamn);
		
		JLabel lblStad = new JLabel("Stad:");
		lblStad.setBounds(48, 95, 61, 16);
		contentPane.add(lblStad);
		
		JButton btnLggTill = new JButton("L\u00E4gg till");
		btnLggTill.setBounds(110, 136, 117, 29);
		contentPane.add(btnLggTill);
		
		btnLggTill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String name = textField.getText();
				String city = textField_1.getText();
				Database DB = new Database();
				String added = DB.AddAirport(city, name);
				JOptionPane.showMessageDialog(null, added + " name");
				textField.setText("");
				textField_1.setText("");
			}
		});
		
		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AirportSwing main = new AirportSwing();
				AddAirport.this.dispose();
				main.setVisible(true);
			}
		});
		btnTillbaka.setBounds(110, 170, 117, 29);
		contentPane.add(btnTillbaka);
	}

}

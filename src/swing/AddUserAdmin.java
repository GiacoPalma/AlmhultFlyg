package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

import app.Database;
import app.EmailValidator;
import app.PhoneValidator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUserAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField firstnameField;
	private JTextField lastnameField;
	private JTextField emailField;
	private JTextField phoneField;
	private JPasswordField passwordField;
	public JCheckBox adminCheckBox;
	public Database DB = new Database();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final AddUserAdmin frame = new AddUserAdmin();
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
	public AddUserAdmin () {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSkapaNyttKonto = new JLabel("Skapa Nytt Konto");
		lblSkapaNyttKonto.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSkapaNyttKonto.setBounds(123, 12, 223, 47);
		contentPane.add(lblSkapaNyttKonto);
		
		firstnameField = new JTextField();
		firstnameField.setBounds(155, 59, 121, 32);
		contentPane.add(firstnameField);
		firstnameField.setColumns(10);
		
		lastnameField = new JTextField();
		lastnameField.setBounds(155, 90, 121, 32);
		contentPane.add(lastnameField);
		lastnameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(155, 123, 121, 32);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setBounds(155, 154, 121, 32);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("F\u00F6rnamn:");
		lblNewLabel.setBounds(46, 68, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEfternamn = new JLabel("Efternamn:");
		lblEfternamn.setBounds(46, 99, 87, 14);
		contentPane.add(lblEfternamn);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(46, 132, 87, 14);
		contentPane.add(lblEmail);
		
		JLabel lblLsenord = new JLabel("Telefonnr:");
		lblLsenord.setBounds(46, 163, 87, 14);
		contentPane.add(lblLsenord);
		
		JLabel lblTelefonnr = new JLabel("L\u00F6senord:");
		lblTelefonnr.setBounds(46, 194, 87, 14);
		contentPane.add(lblTelefonnr);
		
		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminUserControl admin = new AdminUserControl();
				admin.setVisible(true);
				AddUserAdmin.this.dispose();
			}
		});
		btnTillbaka.setBounds(46, 282, 115, 23);
		contentPane.add(btnTillbaka);
		
		JButton btnSkapaNyttKonto = new JButton("Skapa nytt konto");
		btnSkapaNyttKonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailValidator emailValidator = new EmailValidator();
				 PhoneValidator phoneValidator = new PhoneValidator();
				String passWord = new String(passwordField.getPassword()); 
				int admin_status = 0;
				if(adminCheckBox.isSelected()){
					admin_status = 1;
				}else if(adminCheckBox.isSelected() == false){
					admin_status = 0;
				}
				if(!emailValidator.validate(emailField.getText().trim())) {
					   JOptionPane.showMessageDialog(null, "Du måste ange en giltlig Email");
					   emailField.setBackground(Color.red);
				        /*
				           Action that you want to take. For ex. make email id field red
				           or give message box saying invalid email id.
				        */
				   }
				   else if(!phoneValidator.validate(phoneField.getText().trim())) {
					   JOptionPane.showMessageDialog(null, "Du måste ange ett Telefonnummer");
					   phoneField.setBackground(Color.red);
				   }
				   else{String ret = DB.registerUser(emailField.getText(), firstnameField.getText(), lastnameField.getText(), phoneField.getText(), admin_status, passWord);
				JOptionPane.showMessageDialog(null, ret);
				
			}
		}});
		btnSkapaNyttKonto.setBounds(225, 282, 144, 23);
		contentPane.add(btnSkapaNyttKonto);
		
		JLabel lblAdminstatus = new JLabel("Adminstatus:");
		lblAdminstatus.setBounds(46, 232, 87, 14);
		contentPane.add(lblAdminstatus);
		
		adminCheckBox = new JCheckBox("");
		adminCheckBox.setBounds(143, 228, 97, 23);
		contentPane.add(adminCheckBox);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 186, 121, 30);
		contentPane.add(passwordField);
	}
}

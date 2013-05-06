package swing;

import app.Database;
import app.User;
import app.EmailValidator;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AddUser {

	public JFrame frame;
	private JTextField textFieldfirstname;
	private JTextField textFieldemail;
	private JTextField textFieldphone;
	private JTextField textFieldpassword;
	private JLabel lblFrnamn;
	private JLabel lblEfternamn;
	private JLabel lblEmail;
	private JLabel lblTelefonnr;
	private JLabel lblLsenord;
	private JTextField textFieldlastname;
	private JButton btnSkapaKonto;
	private JButton btnTillbaka;
	private Object object;
	public Database DB = new Database();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUser window = new AddUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddUser() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSkapanyttKonto = new JLabel("Skapa nytt konto");
		lblSkapanyttKonto.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSkapanyttKonto.setBounds(123, 12, 223, 47);
		frame.getContentPane().add(lblSkapanyttKonto);
		
		textFieldfirstname = new JTextField();
		textFieldfirstname.setBounds(142, 48, 121, 32);
		frame.getContentPane().add(textFieldfirstname);
		textFieldfirstname.setColumns(10);
		
		textFieldemail = new JTextField();
		textFieldemail.setBounds(142, 115, 121, 32);
		frame.getContentPane().add(textFieldemail);
		textFieldemail.setColumns(10);
		
		textFieldphone = new JTextField();
		textFieldphone.setBounds(142, 147, 121, 32);
		frame.getContentPane().add(textFieldphone);
		textFieldphone.setColumns(10);
		
		textFieldpassword = new JTextField();
		textFieldpassword.setBounds(142, 180, 121, 32);
		frame.getContentPane().add(textFieldpassword);
		textFieldpassword.setColumns(10);
		
		lblFrnamn = new JLabel("Förnamn:");
		lblFrnamn.setBounds(27, 56, 82, 15);
		frame.getContentPane().add(lblFrnamn);
		
		lblEfternamn = new JLabel("Efternamn:");
		lblEfternamn.setBounds(27, 91, 93, 15);
		frame.getContentPane().add(lblEfternamn);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(27, 123, 70, 15);
		frame.getContentPane().add(lblEmail);
		
		lblTelefonnr = new JLabel("Telefonnr:");
		lblTelefonnr.setBounds(27, 156, 97, 15);
		frame.getContentPane().add(lblTelefonnr);
		
		lblLsenord = new JLabel("Lösenord");
		lblLsenord.setBounds(27, 188, 70, 15);
		frame.getContentPane().add(lblLsenord);
		
		textFieldlastname = new JTextField();
		textFieldlastname.setBounds(142, 82, 121, 32);
		frame.getContentPane().add(textFieldlastname);
		textFieldlastname.setColumns(10);
		
		btnSkapaKonto = new JButton("Skapa konto");
		btnSkapaKonto.setBounds(196, 224, 135, 25);
		btnSkapaKonto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				 EmailValidator emailValidator = new EmailValidator();
				   if(!emailValidator.validate(textFieldemail.getText().trim())) {
					   JOptionPane.showMessageDialog(null, "Du måste ange en giltlig Email");
					   AddUser main = new AddUser();
				        /*
				           Action that you want to take. For ex. make email id field red
				           or give message box saying invalid email id.
				        */
				   }
				   else{
				int admin_status = 0;
				String ret = DB.registerUser(textFieldemail.getText(), textFieldfirstname.getText(), textFieldlastname.getText(), textFieldphone.getText(), admin_status, textFieldpassword.getText());
				JOptionPane.showMessageDialog(null, ret);
				Login reload = new Login();
				AddUser.this.frame.dispose();
				reload.frame.setVisible(true);
			}
		}});
		frame.getContentPane().add(btnSkapaKonto);
		
		btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login reload = new Login();
				AddUser.this.frame.dispose();
				reload.frame.setVisible(true);
				}
			});
		btnTillbaka.setBounds(67, 224, 117, 25);
		frame.getContentPane().add(btnTillbaka);
	}
	
	public JTextField getTextField() {
		return textFieldfirstname;
	}
	public JTextField getTextField_1() {
		return textFieldemail;
	}
	public JTextField getTextField_3() {
		return textFieldpassword;
	}
	public JTextField getTextField_2() {
		return textFieldphone;
	}
	public JButton getBtnSkapaKonto() {
		return btnSkapaKonto;
	}
	public JButton getBtnTillbaka() {
		return btnTillbaka;
	}
}

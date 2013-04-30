package swing;

import app.Database;
import app.User;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Scrollbar;

public class AdminUserControl extends JFrame {

	private JPanel contentPane;
	private JTextField firstnameField;
	private JTextField lastnameField;
	private JTextField emailField;
	private JList list;
	private JCheckBox adminCheckBox;
	public Database DB = new Database();
	public List<User>users = new ArrayList<User>();
	private DefaultListModel listModel = new DefaultListModel(); 
	private JTextField phoneField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUserControl frame = new AdminUserControl();
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
	public AdminUserControl() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				int i = list.getSelectedIndex();
				firstnameField.setText(users.get(i).first_name);
				lastnameField.setText(users.get(i).last_name);
				emailField.setText(users.get(i).email);
				phoneField.setText(users.get(i).phonenumber);
				
				if(users.get(i).admin_status == 1){
					adminCheckBox.setSelected(true);
				}else{
					adminCheckBox.setSelected(false);
				}
			}
		});
		
		
		list.setBounds(25, 30, 184, 272);
		contentPane.add(list);
		users = DB.getAllUsers();
		for (int i = 0; i< users.size();i++){
			listModel.addElement(users.get(i).getName()+" ("+users.get(i).email+")");
		}
		
		firstnameField = new JTextField();
		firstnameField.setBounds(390, 30, 99, 20);
		contentPane.add(firstnameField);
		firstnameField.setColumns(10);
		
		lastnameField = new JTextField();
		lastnameField.setBounds(390, 61, 99, 20);
		contentPane.add(lastnameField);
		lastnameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(390, 92, 99, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("F\u00F6rnamn:");
		lblNewLabel.setBounds(310, 33, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Efternamn:");
		lblNewLabel_1.setBounds(310, 64, 83, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("E-mail:");
		lblNewLabel_2.setBounds(310, 95, 61, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblAdminStatus = new JLabel("Adminstatus:");
		lblAdminStatus.setBounds(310, 162, 83, 14);
		contentPane.add(lblAdminStatus);
		
		JLabel lblAccounts = new JLabel("V\u00E4lj Konto");
		lblAccounts.setBounds(89, 11, 61, 14);
		contentPane.add(lblAccounts);
		
		JButton btnUppdatera = new JButton("Uppdatera");
		btnUppdatera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = list.getSelectedIndex();
				User user = users.get(i);
				int adminStatus = 0;
				if(adminCheckBox.isSelected()){
					adminStatus = 1;
				}else if(adminCheckBox.isSelected() == false){
					adminStatus = 0;
				}
				
				String ret = DB.UpdateUser(user.id, emailField.getText(), firstnameField.getText(), lastnameField.getText(), phoneField.getText(), adminStatus);
				JOptionPane.showMessageDialog(null, ret);
				AdminUserControl reload = new AdminUserControl();
				AdminUserControl.this.dispose();
				reload.setVisible(true);
				
			}
		});
		btnUppdatera.setBounds(390, 215, 99, 23);
		contentPane.add(btnUppdatera);
		
		adminCheckBox = new JCheckBox("");
		adminCheckBox.setBounds(390, 158, 97, 23);
		contentPane.add(adminCheckBox);
		
		JButton btnTaBort = new JButton("Ta bort");
		btnTaBort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = list.getSelectedIndex();
				User user = users.get(i);
				String ret = DB.RemoveUser(user.id);
				JOptionPane.showMessageDialog(null, ret);
				AdminUserControl reload = new AdminUserControl();
				AdminUserControl.this.dispose();
				reload.setVisible(true);
			}
		});
		btnTaBort.setBounds(390, 245, 99, 23);
		contentPane.add(btnTaBort);
		
		
		JButton btnNyttKonto = new JButton("Nytt konto");
		btnNyttKonto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddUser Adduser = new AddUser();
				Adduser.frame.setVisible(true);
				setVisible(false);
				
			}
		});
		btnNyttKonto.setBounds(390, 279, 99, 23);
		contentPane.add(btnNyttKonto);
		
		phoneField = new JTextField();
		phoneField.setBounds(390, 123, 99, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblTelefonnr = new JLabel("Telefonnr:");
		lblTelefonnr.setBounds(310, 126, 65, 14);
		contentPane.add(lblTelefonnr);
		
		Scrollbar scrollbar = new Scrollbar();
		scrollbar.setBounds(192, 30, 17, 272);
		contentPane.add(scrollbar);
	}
}

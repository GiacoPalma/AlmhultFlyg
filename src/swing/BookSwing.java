package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.List;
import javax.swing.JButton;
import java.util.ArrayList;
import com.toedter.calendar.JCalendar;
import java.awt.GridLayout;
import app.Airport;
import app.Database;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookSwing extends JFrame {

	public Database DB = new Database();
	private JPanel contentPane;
	private static ArrayList<Airport> airportlist = new ArrayList<Airport>();
	private JList list;
	private DefaultListModel listModel = new DefaultListModel(); 
	private JButton btnTillbaka;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookSwing frame = new BookSwing();
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
	public BookSwing() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		airportlist = (ArrayList<Airport>) DB.getAllAirports();
		
		JComboBox combobox = new JComboBox();
		airportlist = (ArrayList<Airport>) DB.getAllAirports();
		for (int i = 0; i<airportlist.size();i++){
			combobox.addItem(airportlist.get(i).city);		
		}
		combobox.setSelectedIndex(-1);

		combobox.setBounds(10, 25, 185, 20);
		contentPane.add(combobox);
		
		JLabel lblNewLabel = new JLabel("Avreseort");
		lblNewLabel.setBounds(10, 11, 115, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Destination");
		lblNewLabel_1.setBounds(10, 51, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		List list = new List();
		list.setBounds(255, 36, 169, 161);
		contentPane.add(list);
		
		JLabel lblNewLabel_2 = new JLabel("Tillg\u00E4ngliga Flighter");
		lblNewLabel_2.setBounds(254, 11, 144, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		airportlist = (ArrayList<Airport>) DB.getAllAirports();
		for (int i = 0; i<airportlist.size();i++){
			comboBox_1.addItem(airportlist.get(i).city);		
		}
		comboBox_1.setSelectedIndex(-1);

		comboBox_1.setBounds(10, 66, 185, 20);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("N\u00E4sta steg");
		btnNewButton.setBounds(309, 228, 115, 23);
		contentPane.add(btnNewButton);
		  
		  JLabel lblNewLabel_3 = new JLabel("Pris:");
		  lblNewLabel_3.setBounds(255, 203, 46, 14);
		  contentPane.add(lblNewLabel_3);
		  
		  JButton btnSk = new JButton("S\u00F6k");
		  btnSk.setBounds(117, 228, 89, 23);
		  contentPane.add(btnSk);
		  
		  
		  JDateChooser dateChooser = new JDateChooser();
			dateChooser.setDateFormatString("yyyy-MM-dd");
			dateChooser.setBounds(10, 146, 185, 20);
			contentPane.add(dateChooser);
		  
		  JDateChooser dateChooser_1 = new JDateChooser();
		  	dateChooser_1.setDateFormatString("yyyy-MM-dd");
		  	dateChooser_1.setBounds(10, 197, 185, 20);
		  	contentPane.add(dateChooser_1);
		  
		  JRadioButton rdbtnEnkel = new JRadioButton("Enkel");
		  rdbtnEnkel.setBounds(10, 93, 77, 23);
		  contentPane.add(rdbtnEnkel);
		  
		  JRadioButton rdbtnTurRetur = new JRadioButton("Tur & Retur");
		  rdbtnTurRetur.setBounds(89, 93, 109, 23);
		  contentPane.add(rdbtnTurRetur);
		  
		  JLabel lblterresa = new JLabel("\u00C5terresa");
		  lblterresa.setBounds(10, 177, 89, 14);
		  contentPane.add(lblterresa);
		  
		  JLabel lblUtresa = new JLabel("Utresa");
		  lblUtresa.setBounds(10, 123, 46, 14);
		  contentPane.add(lblUtresa);
		  
		  btnTillbaka = new JButton("Tillbaka");
		  btnTillbaka.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		Login logoff = new Login();
		  		dispose();
		  		logoff.frame.setVisible(true);
		  	}
		  });
		  btnTillbaka.setBounds(10, 228, 95, 23);
		  contentPane.add(btnTillbaka);
		  //contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNewLabel, combobox, lblNewLabel_1, comboBox_1, rdbtnEnkel, rdbtnTurRetur, lblUtresa, dateChooser, lblterresa, dateChooser_1, dateChooser.getCalendarButton(), dateChooser_1.getCalendarButton(), btnSk, lblNewLabel_2, lblNewLabel_3, list, btnNewButton}));
	}
	public JButton getBtnTillbaka() {
		return btnTillbaka;
	}
}

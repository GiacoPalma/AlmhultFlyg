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

public class BookSwing extends JFrame {

	public Database DB = new Database();
	private JPanel contentPane;
	private static ArrayList<Airport> airportlist = new ArrayList<Airport>();
	private JList list;
	private DefaultListModel listModel = new DefaultListModel(); 

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
		
		  JCalendar newCalender = new JCalendar();
		  GridLayout gridLayout = (GridLayout) newCalender.getDayChooser().getDayPanel().getLayout();
		  gridLayout.setColumns(7);
		  gridLayout.setRows(7);
		  newCalender.setBounds(10, 97, 185, 129);
		  contentPane.add(newCalender);
		  
		  JLabel lblNewLabel_3 = new JLabel("Pris:");
		  lblNewLabel_3.setBounds(255, 203, 46, 14);
		  contentPane.add(lblNewLabel_3);
		  
		  JButton btnSk = new JButton("S\u00F6k");
		  btnSk.setBounds(10, 228, 89, 23);
		  contentPane.add(btnSk);
	}
}

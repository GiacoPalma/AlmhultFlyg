package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.List;
import javax.swing.JButton;

import com.toedter.calendar.JCalendar;

public class BookSwing extends JFrame {

	private JPanel contentPane;

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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 185, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Avreseort");
		lblNewLabel.setBounds(10, 11, 115, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Destination");
		lblNewLabel_1.setBounds(10, 67, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		List list = new List();
		list.setBounds(229, 36, 169, 161);
		contentPane.add(list);
		
		JLabel lblNewLabel_2 = new JLabel("Tillg\u00E4ngliga Flighter");
		lblNewLabel_2.setBounds(229, 11, 144, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 91, 185, 20);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("N\u00E4sta steg");
		btnNewButton.setBounds(283, 228, 115, 23);
		contentPane.add(btnNewButton);
		
		  JCalendar newCalender = new JCalendar();
		  newCalender.setBounds(10, 133, 185, 129);
		  contentPane.add(newCalender);
		  
		  JLabel lblNewLabel_3 = new JLabel("Pris:");
		  lblNewLabel_3.setBounds(229, 203, 46, 14);
		  contentPane.add(lblNewLabel_3);
	}
}

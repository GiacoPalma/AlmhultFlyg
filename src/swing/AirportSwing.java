package swing;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import app.Airport;
import app.Database;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;

public class AirportSwing extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public Database DB = new Database();
	private JButton btnLggTill;
	private JButton btnTaBort;
	private JButton btnRedigera;
	private static List<Airport> airportlist = new ArrayList<Airport>();
	private JList list;
	private DefaultListModel listModel = new DefaultListModel(); 
	private JButton btnLggTillFlygplats;
	

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
		setTitle("Redigera flygplats");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		airportlist = DB.getAllAirports();
		
		
		
		JLabel lblFlygplatser = new JLabel("Flygplatser:");
		lblFlygplatser.setBounds(43, 49, 99, 16);
		contentPane.add(lblFlygplatser);
		
		btnLggTill = new JButton("Lägg till ny flygplats");
		
		btnRedigera = new JButton("Uppdatera");
		btnRedigera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = list.getSelectedIndex();
				String city = textField_1.getText();
				String name = textField.getText();
				String updated = DB.UpdateAirport(airportlist.get(i).id, textField_1.getText(), textField.getText());
				JOptionPane.showMessageDialog(null, updated);
				AirportSwing reload = new AirportSwing();
				AirportSwing.this.dispose();
				reload.setVisible(true);
				reload.textField.setText(name);
				reload.textField_1.setText(city);
				reload.list.setSelectedIndex(i);
			}
		});
		btnRedigera.setBounds(331, 128, 134, 29);
		contentPane.add(btnRedigera);
		
		btnTaBort = new JButton("Ta bort");
		btnTaBort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = list.getSelectedIndex();
				String removed = DB.RemoveAirport(airportlist.get(i).id);
				JOptionPane.showMessageDialog(null, removed);
				AirportSwing reload = new AirportSwing();
				AirportSwing.this.dispose();
				reload.setVisible(true);
			}
		});		
		btnTaBort.setBounds(331, 156, 134, 29);
		contentPane.add(btnTaBort);
		
		textField = new JTextField();
		textField.setBounds(331, 66, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(331, 100, 134, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		

		
		JLabel lblNamn = new JLabel("Namn:");
		lblNamn.setBounds(275, 72, 61, 16);
		contentPane.add(lblNamn);
		
		JLabel lblStad = new JLabel("Stad:");
		lblStad.setBounds(275, 106, 61, 16);
		contentPane.add(lblStad);
		
		list = new JList(listModel);
		for (int i = 0; i<airportlist.size();i++){
			listModel.addElement(airportlist.get(i).name);		
		}
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				int i = list.getSelectedIndex();
				textField.setText(airportlist.get(i).name);
		    	textField_1.setText(airportlist.get(i).city);
			}
		});
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		list.setBounds(43, 76, 184, 206);
		contentPane.add(list);
		JLabel lbllmhultFlygplatsadmin = new JLabel("\u00C4lmhult flygplats (Admin)");
		lbllmhultFlygplatsadmin.setFont(new Font("Helvetica", Font.BOLD, 24));
		lbllmhultFlygplatsadmin.setBounds(60, 0, 399, 37);
		contentPane.add(lbllmhultFlygplatsadmin);
		
		JButton btnLggTillFlygning = new JButton("L\u00E4gg till flygning");
		btnLggTillFlygning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLggTillFlygning.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FlightSwing flightSwing = new FlightSwing();
				flightSwing.setVisible(true);
				setVisible(false);
			}
		});
		btnLggTillFlygning.setBounds(300, 244, 165, 29);
		contentPane.add(btnLggTillFlygning);
		
		btnLggTillFlygplats = new JButton("Lägg till flygplats");
		btnLggTillFlygplats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AddAirport AddAirport = new AddAirport();
				AddAirport.setVisible(true);
				setVisible(false);
			}
		});
		btnLggTillFlygplats.setBounds(300, 207, 165, 29);
		contentPane.add(btnLggTillFlygplats);
	}
	public JButton getBtnLggTill() {
		return btnLggTill;
	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JButton getBtnTaBort() {
		return btnTaBort;
	}
	public JButton getBtnRedigera() {
		return btnRedigera;
	}
	public JPanel getContentPane() {
		return contentPane;
	}
	public JList getList() {
		return list;
	}
	public JButton getBtnLggTillFlygplats() {
		return btnLggTillFlygplats;
	}
}

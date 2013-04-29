package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import app.Database;
import app.Flight;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class DeleteFlight extends JFrame {

	private JPanel contentPane;
	public Database database = new Database();
	private DefaultListModel listModel = new DefaultListModel();
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteFlight frame = new DeleteFlight();
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
	public DeleteFlight() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final List<Flight> allFlights = database.getAllFlights();
		list = new JList(listModel);

		for (int i = 0; i < allFlights.size(); i++) {
			listModel.addElement("FrŒn: "
					+ allFlights.get(i).getAirport().getName() + " Till: "
					+ allFlights.get(i).getDest_airport().getName()
					+ "Avresetid: " + allFlights.get(i).getDepature_date()
					+ "Destinationstid: "
					+ allFlights.get(i).getDestination_date() + "Pris: "
					+ allFlights.get(i).getPrice());
		}

		JLabel lblTaBortFlygning = new JLabel("Ta bort flygning");
		lblTaBortFlygning.setFont(new Font("Helvetica", Font.BOLD, 24));
		lblTaBortFlygning.setBounds(37, 28, 196, 28);
		contentPane.add(lblTaBortFlygning);

		JButton btnTaBort = new JButton("Ta bort");
		btnTaBort.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int i = list.getSelectedIndex();
				int id = allFlights.get(i).getId();
				System.out.println(id + "testid");

				Object[] options = { "Yes, please", "No way!" };
				int n = JOptionPane.showOptionDialog(new JFrame(),
						"Would you like green eggs and ham?",
						"A Silly Question", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, // do not use a
															// custom Icon
						options, // the titles of buttons
						options[0]); // default button title

				if(n==0){
				 database.RemoveFlight(id);
				 System.out.println(n);
				 }
			}
		});

		list = new JList(listModel);
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		list.setBounds(37, 66, 507, 224);
		contentPane.add(list);

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				int i = list.getSelectedIndex();
			}
		});

		btnTaBort.setBounds(37, 302, 117, 29);
		contentPane.add(btnTaBort);

		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.setBounds(37, 331, 117, 29);
		contentPane.add(btnTillbaka);

	}
}

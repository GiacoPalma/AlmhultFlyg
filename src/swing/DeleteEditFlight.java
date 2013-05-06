package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class DeleteEditFlight extends JFrame {

	private JPanel contentPane;
	public Database database = new Database();
	private DefaultListModel listModel = new DefaultListModel();
	private JList list;
	private JList list_1;
	private int selectedId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEditFlight frame = new DeleteEditFlight();
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
	public DeleteEditFlight() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final List<Flight> allFlights = database.getAllFlights();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 66, 700, 224);
		contentPane.add(scrollPane);
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		for (int i = 0; i < allFlights.size(); i++) {
			listModel.addElement(allFlights.get(i).getAirport().getName()
					+ " - " + allFlights.get(i).getDest_airport().getName()
					+ " | " + allFlights.get(i).getDepature_date() + " - "
					+ allFlights.get(i).getDestination_date() + " | Pris: "
					+ allFlights.get(i).getPrice() + " kr");
		}

		JLabel lblTaBortFlygning = new JLabel("Ta bort/redigera flygning");
		lblTaBortFlygning.setFont(new Font("Helvetica", Font.BOLD, 24));
		lblTaBortFlygning.setBounds(37, 28, 321, 28);
		contentPane.add(lblTaBortFlygning);

		JButton btnTaBort = new JButton("Ta bort");
		btnTaBort.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int i = list.getSelectedIndex();
				if (i >= 0) {
					int id = allFlights.get(i).getId();

					Object[] options = { "Ja", "Nej" };
					int n = JOptionPane.showOptionDialog(new JFrame(),
							"Vill du ta bort flygningen?",
							"Bekr�fta borttagning", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);

					if (n == 0) {
						database.RemoveFlight(id);
						dispose();
						DeleteEditFlight delEdit = new DeleteEditFlight();
						delEdit.setVisible(true);
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Du m�ste v�lja en flygning att ta bort/redigera",
							"Dialog", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnTaBort.setBounds(37, 302, 117, 29);
		contentPane.add(btnTaBort);

		JButton btnTillbaka = new JButton("Tillbaka");
		btnTillbaka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				AdminMenu adminMenu = new AdminMenu();
				adminMenu.setVisible(true);
				
			}
		});
		btnTillbaka.setBounds(37, 331, 117, 29);
		contentPane.add(btnTillbaka);

		JButton btnRedigera = new JButton("Redigera");
		btnRedigera.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (list.getSelectedIndex() >= 0) {
					selectedId = list.getSelectedIndex();
					int sendId = allFlights.get(selectedId).getId();
					EditSwing editFlight = new EditSwing(sendId);
					editFlight.setId(selectedId);
					editFlight.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Du m�ste v�lja en flygning att ta bort/redigera",
							"Dialog", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnRedigera.setBounds(166, 302, 117, 29);
		contentPane.add(btnRedigera);

	}
}

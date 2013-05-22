package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import app.Booking;
import app.Database;
import app.Flight;
import app.User;

public class listResults extends JPanel implements MouseListener{

	/**
	 * Create the panel.
	 * 
	 * @return 
	 */

	
	JPanel panel_1 = new JPanel();


	

	
	public JPanel listResults(List<Flight> flights, String airportnameDep, String airportnameDest, String airportnameDestR2, String airportnameDepR2, String dep_date, String dest_date, String dep_date2, String dest_date2, String priceR2, String price, int id, User user) {
		GridBagLayout layoutpane = new GridBagLayout();
		
		final int listID = id;
		final List<Flight> flightss = flights;
		final User user1 = user;
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setSize(560, 50);
		Dimension dimension = new Dimension(560,50);
		panel_1.setMinimumSize(dimension);
		panel_1.setLayout(layoutpane);
		
		JTextPane txtpnAlmhult = new JTextPane();
		JTextPane txtpnRoute2 = new JTextPane();
		JTextPane txtpnPrice = new JTextPane();
		txtpnAlmhult.setEditable(false);
		txtpnAlmhult.setBackground(Color.DARK_GRAY);
		txtpnAlmhult.setForeground(Color.WHITE);
		txtpnAlmhult.setSize(100, 30);
		txtpnAlmhult.setText(airportnameDep + " - " + airportnameDest);
		GridBagConstraints c = new GridBagConstraints();
		GridBagConstraints d = new GridBagConstraints();
		GridBagConstraints e = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridheight = 1;
		c.gridwidth = 1;
		panel_1.add(txtpnAlmhult, c);
		if(airportnameDestR2 != "" && airportnameDepR2 != ""){
			txtpnRoute2.setEditable(false);
			txtpnRoute2.setBackground(Color.DARK_GRAY);
			txtpnRoute2.setForeground(Color.white);
			txtpnRoute2.setSize(100, 30);
			txtpnRoute2.setText(airportnameDepR2+ " - "+ airportnameDestR2);
			d.gridx = 0;
			d.gridy = 1;
			d.gridheight = 1;
			d.fill = GridBagConstraints.HORIZONTAL;
			d.gridwidth = 1;
			d.weightx = 0.5;
			panel_1.add(txtpnRoute2, d);
		}
		txtpnPrice.setEditable(false);
		txtpnPrice.setBackground(Color.DARK_GRAY);
		txtpnPrice.setForeground(Color.white);
		txtpnPrice.setSize(60, 30);
		if(priceR2 == ""){
			txtpnPrice.setText("Price: "+price);
		}else{
			txtpnPrice.setText("Price: "+priceR2);
		}
		e.gridx = 4;
		e.gridy = 0;
		e.weightx = 0.5;
		e.gridheight = 1;
		e.gridwidth = 1;
		panel_1.add(txtpnPrice, e);
		JButton btnNewButton = new JButton("Boka");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Booking booking = new Booking();
				String errorMessage;
				
				
				if(flightss.get(listID).route1.available){
					errorMessage = "Du m�ste v�lja en flygning.";
				} else {
					errorMessage = "Flygningen �r fullbokad";
				}
				
				
				if(listID >= 0 && flightss.get(listID).route1.available){
					boolean booked = Database.AddBooking(flightss.get(listID), user1);
					if(booked){
						Object[] options = {"OK"};
						
						int n = JOptionPane.showOptionDialog(new JFrame(),
				                   "Flygningen �r bokad","Title",
				                   JOptionPane.PLAIN_MESSAGE,
				                   JOptionPane.QUESTION_MESSAGE,
				                   null,
				                   options,
				                   options[0]);
						if(n==0){
							UserMenu reload = new UserMenu(user1);
							reload.dispose();
							reload.setVisible(true);
						}
						
					} else {
						JOptionPane.showMessageDialog(new JFrame(),
								"N�gontingting gick fel.",
								"Dialog", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							errorMessage,
							"Dialog", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNewButton.setSize(115, 20);
		GridBagConstraints f = new GridBagConstraints();
		f.gridx = 4;
		f.gridy = 1;
		f.gridheight = 1;
		f.gridwidth = 1;
		f.weightx = 0.2;
		panel_1.add(btnNewButton, f);
		
		return panel_1;

	}





	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
		
	}





	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
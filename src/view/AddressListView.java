package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import control.listeners.AddEmailButtonActionListener;
import control.listeners.AddPostalActionListener;
import control.listeners.SaveButtonActionListener;

import model.AbstractAddress;
import model.AddressList;

@SuppressWarnings("serial")
public class AddressListView extends JFrame implements Observer {
	
	// member fields, the AddressList to display and a ListModel
	private AddressList addressList;
	private DefaultListModel listModel;

	// create a AddressListView
	public AddressListView(AddressList addressList) {
		System.out.println("constructing AddressListView");
		this.addressList = addressList;
		addressList.addObserver(this);
		
		init();
		populateFields();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	// Setting up the view
	private void init() {
		System.out.println("initilazing AddressListView");

		// setting Jframe (Window) title and layout
		this.setTitle("Address-List");
		this.setLayout(new GridBagLayout());
		
		// creating the constraints for the JScrollPane
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.weighty = 0.9;
		constraints.weightx = 0.9;

		// creating the JScrollPane
		listModel = new DefaultListModel();
		JList list = new JList(listModel);
		JScrollPane scrollpane = new JScrollPane(list);
		
		// adding the JScrollPane to the JFrame with its specific constraints
		this.add(scrollpane, constraints);
		
		// creating a addButton and registering a new listener
		JButton addEmailButton = new JButton("Add Email");
		final AddressListView alv = this;
		addEmailButton.addActionListener(new AddEmailButtonActionListener(alv));
			
		// changing the constraints to go with the buttons
		// adding the button to the JFrame
		constraints.fill = GridBagConstraints.NONE;
		constraints.weighty = 0;
		constraints.weightx = 0;
		constraints.gridwidth = 1;
		
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		
		this.add(addEmailButton, constraints);
		
		JButton addPostalButton = new JButton("Add Postal");
		addPostalButton.addActionListener(new AddPostalActionListener(alv));		
				
		constraints.gridx = 1;
		constraints.gridy = 1;
		this.add(addPostalButton, constraints);

		// creating a saveButton abd registering a new listener
		JButton saveButton = new JButton("Save all");
		saveButton.addActionListener(new SaveButtonActionListener(addressList));
					
		// changing the constraints to fit to the button
		// adding the button to the JFrame
		constraints.gridx = 2;
		constraints.gridy = 1;
			
		this.add(saveButton, constraints);
		
		// packing the whole window together
		this.pack();
	}
	
	// adding an address to the addressList-model
	public void addAddress(AbstractAddress address) {
		System.out.println("ALV: adding address");
		addressList.add(address);
	}

	// filling the ListModel with addresses
	private void populateFields() {
		System.out.println("ALV: populating Fields");
		refreshAddressList();
	}

	// getting the addresses from the model
	private void refreshAddressList() {

		System.out.println("ALV: refreshing AddressListView");
		listModel.removeAllElements();
		for (AbstractAddress address : addressList.getAddressList()) {
			listModel.addElement(address.toString());
		}
	}

	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("ALV: updating...");
		populateFields();
	}
	
	
}

package model;

import java.io.Serializable;

public abstract class AbstractAddress implements Serializable {

	private static final long serialVersionUID = 6780532528672421304L;
	
	// member fields for the address
	private String name;
	private String emailaddress;

	public AbstractAddress() {
		System.out.println("constructing AbstractAddress");
		name = "";
		emailaddress = "";
	}
	
	// initializing the values
	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getEmailaddress() {
		return emailaddress;
	}
	
	// overriding the toString()-method
	public String toString() {
		return String.format("%s %s", name, emailaddress);
	}
}
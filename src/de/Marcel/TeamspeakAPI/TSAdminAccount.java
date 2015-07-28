package de.Marcel.TeamspeakAPI;

public class TSAdminAccount {
	private String username, password;
	
	public TSAdminAccount (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
}

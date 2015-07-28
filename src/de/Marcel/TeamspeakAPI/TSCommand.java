package de.Marcel.TeamspeakAPI;

public class TSCommand {
	private String commandString;
	private String commandType;

	public void build(String commandString, String commandType) {
		this.commandString = commandString;
		this.commandType = commandType;
	}

	public String getCommandString() {
		return commandString;
	}

	public String getCommandType() {
		return commandType;
	}

	public static class MessageCommand extends TSCommand {
		public MessageCommand(String message) {
			this.build("sendtextmessage targetmode=2 target=1 msg=" + message,
					"MESSAGE");
		}
	}

	public static class UseCommand extends TSCommand {
		public UseCommand(int serverid) {
			this.build("use sid=" + serverid, "USE");
		}
	}

	public static class LoginCommand extends TSCommand {
		public LoginCommand(String username, String password) {
			this.build("login client_login_name=" + username
					+ " client_login_password=" + password, "LOGIN");
		}
	}

	public static class AddServerGroupCommand extends TSCommand {
		public AddServerGroupCommand(String name, String type) {
			this.build("servergroupadd name=" + name + " " + type,
					"ADDSERVERGROUP");
		}
	}

	public static class FindClientID extends TSCommand {
		public FindClientID(String name) {
			this.build("clientfind pattern=" + name, "FINDCLIENTID");
		}
	}

	public static class KickClientByID extends TSCommand {
		public KickClientByID(int id, String reason) {
			this.build("clientkick clid=" + id + " reasonid=5 reasonmsg="
					+ reason, "KICKCLIENTBYID");
		}
	}
}
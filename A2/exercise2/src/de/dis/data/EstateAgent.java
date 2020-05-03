package de.dis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EstateAgent {
	// private int id = -1;
	private String login;
	private String name;
	private String address;
	private String password;

	// public int getId() { return this.id; }
	// public void setId(int id) { this.id = id; }
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		String agentString = this.getLogin() + ", " + this.getName() + ", " + this.getAddress() + ", "
				+ this.getPassword();
		return agentString;
	}

	public void save() {
		Connection con = DbConnectionManager.getInstance().getConnection();

		try {
			
			// Check if login name already exists 
			if (read(getLogin()) != null) {
				System.out.println("\nLogin name [" + getLogin() + "] already exists. Agent not saved.\n");
				return;
			}
			
			String insertSQL = "INSERT INTO estate_agent(agent_login, agent_name, agent_address, agent_password) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, getLogin());
			pstmt.setString(2, getName());
			pstmt.setString(3, getAddress());
			pstmt.setString(4, getPassword());
			pstmt.executeUpdate();
			pstmt.close();
			System.out.println("\nAgent [" + toString() + "] created.\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static EstateAgent read(String login) {
		try {
			Connection con = DbConnectionManager.getInstance().getConnection();

			String selectSQL = "SELECT * FROM estate_agent WHERE agent_login = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, login);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				EstateAgent estateAgent = new EstateAgent();
				estateAgent.setLogin(login);
				estateAgent.setName(rs.getString("agent_name"));
				estateAgent.setAddress(rs.getString("agent_address"));
				estateAgent.setPassword(rs.getString("agent_password"));

				rs.close();
				pstmt.close();

				return estateAgent;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void update(EstateAgent estateAgent) {
	}

	public void delete(String login) {
	}
}

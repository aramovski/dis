package de.dis.data;

import de.dis.FormUtil;

import java.text.Normalizer;

public class EstateAgentManager {
	
	public static void newEstateAgent() {
		EstateAgent agent = new EstateAgent();

		agent.setLogin(FormUtil.readString("Login"));
		agent.setName(FormUtil.readString("Name"));
		agent.setAddress(FormUtil.readString("Address"));
		agent.setPassword(FormUtil.readString("Password"));		
		agent.save();
	}
	
	public static void updateEstateAgent() {
		EstateAgent newAgent = new EstateAgent();

		String oldLogin = FormUtil.readString("Login from Agent you want to update");

		newAgent.setLogin(FormUtil.readString("New Login"));
		newAgent.setName(FormUtil.readString("New Name"));
		newAgent.setAddress(FormUtil.readString("New Address"));
		newAgent.setPassword(FormUtil.readString("New Password"));
		newAgent.update(oldLogin, newAgent);
	}

}

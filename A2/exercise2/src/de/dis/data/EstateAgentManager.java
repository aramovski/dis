package de.dis.data;

import de.dis.FormUtil;

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
		EstateAgent agent = new EstateAgent();
		agent.setLogin(FormUtil.readString("Get Agent by Login"));
		
		EstateAgent tmpAgent = agent.read(agent.getLogin());
		System.out.println("Agent loaded: [" + tmpAgent.toString() + "]");

		agent.setLogin(FormUtil.readString("New Login"));
		agent.setLogin(FormUtil.readString("New Name"));
		agent.setAddress(FormUtil.readString("New Address"));
		agent.setPassword(FormUtil.readString("New Password"));
		agent.save();

		System.out.println("Agent with Login [" + agent.getLogin() + "] created.");
	}

}

package de.dis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import de.dis.data.EstateAgentManager;
import de.dis.data.Makler;

/**
 * Hauptklasse
 */
public class Main {
	
	final static String PASSWORD = "password";
	
	
	/**
	 * Startet die Anwendung
	 */
	public static void main(String[] args) {
		showMainMenu();
	}

	/**
	 * Zeigt das Hauptmenü
	 */
	public static void showMainMenu() {
		// Menüoptionen
		final int MENU_AGENT = 0;
		final int MENU_ESTATE = 1;
		final int MENU_CONTRACT = 2;
		final int QUIT = 3;

		// Erzeuge Menü
		Menu mainMenu = new Menu("Main Menu");
		mainMenu.addEntry("Estate Agent Management", MENU_AGENT);
		mainMenu.addEntry("Estate Management", MENU_ESTATE);
		mainMenu.addEntry("Contract Management", MENU_CONTRACT);
		mainMenu.addEntry("Exit", QUIT);

		// Verarbeite Eingabe
		while (true) {
			int response = mainMenu.show();

			switch (response) {
			case MENU_AGENT:
				// showMaklerMenu();
				if (checkPassword()) showEstateAgentMenu();
				return;
			case MENU_ESTATE:
				showEstateMenu();
				break;
			case MENU_CONTRACT:
				showContractMenu();
				break;
			case QUIT:
				System.out.println("Bye!");
				return;
			}
		}
	}
	
	private static boolean checkPassword() {
		System.out.println("Please enter password (='" + PASSWORD + "'): ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		if (input.equalsIgnoreCase(PASSWORD)) {
			System.out.println("Correct!\n\n");
			return true;
		} else {
			System.out.println("Wrong password. Access denied!");
			scanner.close();
			return false;
		}
	}
	
	

	public static void showEstateAgentMenu() {
		final int CREATE_AGENT = 0;
		final int UPDATE_AGENT = 1;
		final int DELETE_AGENT = 2;
		final int BACK = 3;

		Menu estateAgentMenu = new Menu("Estate Agent Management");
		estateAgentMenu.addEntry("Create Agent", CREATE_AGENT);
		estateAgentMenu.addEntry("Update Agent", UPDATE_AGENT);
		estateAgentMenu.addEntry("Delete Agent", DELETE_AGENT);
		estateAgentMenu.addEntry("Back to Main Menu", BACK);

		while (true) {
			int response = estateAgentMenu.show();

			switch (response) {
			case CREATE_AGENT:
				EstateAgentManager.newEstateAgent();
				break;
			case UPDATE_AGENT:
				EstateAgentManager.updateEstateAgent();
				break;
			case DELETE_AGENT:
				//newMakler();
				break;
			case BACK:
				return;
			}
		}
	}
	
	public static void showEstateMenu() {

	}

	public static void showContractMenu() {

	}

	/**
	 * Zeigt die Maklerverwaltung
	 */
	public static void showMaklerMenu() {
		// Menüoptionen
		final int NEW_MAKLER = 0;
		final int BACK = 1;

		// Maklerverwaltungsmenü
		Menu maklerMenu = new Menu("Makler-Verwaltung");
		maklerMenu.addEntry("Neuer Makler", NEW_MAKLER);
		maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);

		// Verarbeite Eingabe
		while (true) {
			int response = maklerMenu.show();

			switch (response) {
			case NEW_MAKLER:
				newMakler();
				break;
			case BACK:
				return;
			}
		}
	}

	/**
	 * Legt einen neuen Makler an, nachdem der Benutzer die entprechenden Daten
	 * eingegeben hat.
	 */
	public static void newMakler() {
		Makler m = new Makler();

		m.setName(FormUtil.readString("Name"));
		m.setAddress(FormUtil.readString("Adresse"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPassword(FormUtil.readString("Passwort"));
		m.save();

		System.out.println("Makler mit der ID " + m.getId() + " wurde erzeugt.");
	}
}

package com.amr.rpg.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.amr.rpg.constants.Constants;

/**
 * @author aeldemerdash
 *
 */
public class Menu {
	private final String name;
	static Scanner scanner;

	private LinkedHashMap<String, Runnable> menusCallbacks = new LinkedHashMap<>();

	public Menu(String name) {
		this.name = name;
	}

	public void registerAction(String name, Runnable action) {
		Objects.requireNonNull(name,"Name must not be null");
		Objects.requireNonNull(action, "action must not be null");
		menusCallbacks.put(name, action);
	}

	public String buildMenuAsText() {
		StringBuilder menuAsText = new StringBuilder();
		menuAsText.append(Constants.ANSI_BLUE).append(name).append(":\n").append(Constants.ANSI_BLACK);
		List<String> actionNames = new ArrayList<>(menusCallbacks.keySet());
		for (int i = 0; i < actionNames.size(); i++) {
			menuAsText.append(String.format(" %d: %s%n", i + 1, actionNames.get(i)));
		}
		menuAsText.append("Please choose an option from the Above List : ");
		return menuAsText.toString();
	}

	public void process(int actionNumber) {
		actionNumber--;
		if (actionNumber < 0 || actionNumber >= menusCallbacks.size()) {
			System.out.println("Please select a valid option: ");
		} else {
			List<Runnable> actions = new ArrayList<>(menusCallbacks.values());
			actions.get(actionNumber).run();
		}
	}

	public static void activate(Menu menu) {
		System.out.print(menu.buildMenuAsText());
		scanner = new Scanner(System.in);
		while (true) {
			int actionNumber = scanner.nextInt();
			menu.process(actionNumber);
		}
	}
}

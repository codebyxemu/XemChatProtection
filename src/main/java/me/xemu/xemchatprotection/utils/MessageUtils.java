package me.xemu.xemchatprotection.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class MessageUtils {

	public static String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static boolean containsString(String string, List<String> strings) {
		for (String s : strings) {
			if (string.contains(s)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsString(String string, String[] strings) {
		for (String s : strings) {
			if (string.contains(s)) {
				return true;
			}
		}
		return false;
	}

}

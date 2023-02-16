package me.xemu.xemchatprotection.utils;

import org.bukkit.ChatColor;

public class MessageUtils {

	public static String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

}

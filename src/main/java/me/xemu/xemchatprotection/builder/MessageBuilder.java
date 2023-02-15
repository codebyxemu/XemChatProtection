package me.xemu.xemchatprotection.builder;

import me.xemu.xemchatprotection.XemChatProtection;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageBuilder {

	private String message;

	public MessageBuilder(String message) {
		this.message = message;
	}

	public MessageBuilder(String message, boolean prefix, boolean colors) {
		this.message = message;
		if (colors) this.message = ChatColor.translateAlternateColorCodes('&', message);
		if (prefix) prefix();
	}

	public MessageBuilder placeholder(String placeholder, String replace) {
		this.message = message.replaceAll(placeholder, replace);
		return this;
	}

	public MessageBuilder colors() {
		this.message = ChatColor.translateAlternateColorCodes('&', message);
		return this;
	}

	public MessageBuilder prefix() {
		placeholder("<prefix>", XemChatProtection.INSTANCE.getConfig().getString("Prefix"));
		return this;
	}

	public void send(Player player) {
		player.sendMessage(message);
	}

	public String getMessage() {
		return message;
	}
}

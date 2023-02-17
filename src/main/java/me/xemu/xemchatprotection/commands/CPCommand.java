package me.xemu.xemchatprotection.commands;

import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.builder.MessageBuilder;
import me.xemu.xemchatprotection.manager.ConfigurationManager;
import me.xemu.xemchatprotection.utils.Word;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CPCommand implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player)) return true;

		Player player = (Player) sender;
		boolean staff = player.hasPermission(XemChatProtection.INSTANCE.getConfiguration().getString("StaffPermission"));

		if (args.length == 0) {
			helpPage(player, staff);
			return true;
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("words")) {
				new MessageBuilder("&8>> &eSearching for words...").colors().send(player);
				for (Word word : ConfigurationManager.getWords()) {
					new MessageBuilder("&8--------------------------------------").colors().send(player);
					new MessageBuilder("&8&l>> &eWord: &f" + word.getWord()).colors().send(player);
					if (word.getAliases() != null) {
						new MessageBuilder("&8&l>> &eAliases: &f" + word.getAliases()).colors().send(player);
					}
					if (word.getIgnoreWith() != null) {
						new MessageBuilder("&8&l>> &eIgnore With: &f" + word.getIgnoreWith()).colors().send(player);
					}
					new MessageBuilder("&8--------------------------------------").colors().send(player);
				}
				new MessageBuilder("&8>> &eTotal Words: &f" + ConfigurationManager.getWords().size());
			} else if (args[0].equalsIgnoreCase("support")) {
				boolean supported = false;
				if (XemChatProtection.INSTANCE.getDescription().getVersion().contains("1_19")) {
					supported = true;
				}

				if (supported) {
					new MessageBuilder("&8>> &3Xemu's Chat Protection&7 is running on a &aSupported Server&7.")
							.colors()
							.send(player);
				} else {
					new MessageBuilder("&8>> &3Xemu's Chat Protection&7 is running on a &c&lUnsupported Server&7.")
							.colors()
							.send(player);
				}
			} else if (args[0].equalsIgnoreCase("reload")) {
				new MessageBuilder("&8>> &3Configurations reloaded!")
						.colors()
						.send(player);
				ConfigurationManager.reloadConfigurations();
			} else {
				helpPage(player, staff);
				return true;
			}
		} else {
			helpPage(player, staff);
			return true;
		}



		return true;
	}

	private void helpPage(Player player, boolean staff) {
		if (staff) {
			String[] staffHelp = new String[]{
					"&3&lXemu's Chat Protection &8&l>> &fVersion: " + XemChatProtection.INSTANCE.getDescription().getVersion(),
					"&7/cp words &b> &fView all of the words.",
					"&7/cp support &b> &fCheck if your server is supported."
			};

			for (String part : staffHelp) {
				new MessageBuilder(part).colors().send(player);
			}
		} else {
			String[] staffHelp = new String[]{
					"&3&lXemu's Chat Protection &8&l>> &fVersion: " + XemChatProtection.INSTANCE.getDescription().getVersion(),
					"&7Thank you for using Xemu Chat Protection."
			};

			for (String part : staffHelp) {
				new MessageBuilder(part).colors().send(player);
			}
		}
	}

}

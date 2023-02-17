package me.xemu.xemchatprotection.manager;

import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.utils.Word;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager {

	private XemChatProtection plugin = XemChatProtection.INSTANCE;

	public ConfigurationManager() {
		plugin.getConfiguration().setDefault("Prefix", "&8[&3Chat&bProtection&8]");
		plugin.getConfiguration().setDefault("StaffPermission", "xcp.staff");
		plugin.getConfiguration().setDefault("Settings.ProfanityShield", true);
		plugin.getConfiguration().setDefault("Settings.AdvertiseShield", true);

		plugin.getConfiguration().setDefault("Messages.Alerts.AlertMessage", new String[]{
				"&8&m-------------- &r &c&lBlocked Message&r &8&m--------------",
				"&7User: &e<offender>",
				"&7Response-Code: &4<responseCode>",
				"&7Blocked Message: &c<blockedMessage>",
				"&8&m-------------- &r &c&lBlocked Message&r &8&m--------------",
		});
		plugin.getConfiguration().setDefault("Messages.Alerts.ProfanityMessage", "<prefix> &cMessage cannot be sent. Includes profanity!");
		plugin.getConfiguration().setDefault("Messages.Alerts.LinkMessage", "<prefix> &cMessage cannot be sent. Includes a blacklisted link!");

		plugin.getWords().setDefault("Words.ConfigureMe.aliases", new String[]{});

		plugin.getWords().setDefault("WhitelistedLinks", new String[]{
				"https://google.com"
		});

		plugin.getWords().setDefault("LinkContains", new String[]{
			"https://", "http://", "discord.gg/"
		});

	}

	public static List<String> getProfanityShield() {
		return XemChatProtection.INSTANCE.getWords().getStringList("ProfanityShield");
	}

	public static List<Word> getWords() {
		List<Word> words = new ArrayList<>();

		for (String word : XemChatProtection.INSTANCE.getWords().keySet("Words")) {
			Word wordFromConfig = new Word(
					word.replaceAll(".aliases", ""),
					XemChatProtection.INSTANCE.getWords().getStringList("Words." + word + ".aliases")
			);

			words.add(wordFromConfig);
		}

		return words;
	}

	public static List<String> getWhitelistedLinks() {
		return XemChatProtection.INSTANCE.getWords().getStringList("WhitelistedLinks");
	}

	public static List<String> getLinkContains() {
		return XemChatProtection.INSTANCE.getWords().getStringList("LinkContains");
	}

	public static void reloadConfigurations() {
		XemChatProtection.INSTANCE.getConfiguration().forceReload();
		XemChatProtection.INSTANCE.getWords().forceReload();
	}

}
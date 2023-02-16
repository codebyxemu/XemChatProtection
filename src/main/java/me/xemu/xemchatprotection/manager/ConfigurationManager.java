package me.xemu.xemchatprotection.manager;

import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.word.Word;
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

		plugin.getWords().setDefault("ProfanityShield", new String[]{
				"CONFIGURE_HERE"
		});

		plugin.getWords().setDefault("WhitelistedLinks", new String[]{
				"https://google.com"
		});

		plugin.getWords().setDefault("LinkContains", new String[]{
			"https://", "http://", "discord.gg/"
		});

	}

	@Deprecated
	public static List<String> getProfanityShield() {
		return XemChatProtection.INSTANCE.getWords().getStringList("ProfanityShield");
	}

	public static List<Word> getWords() {
		List<Word> words = new ArrayList<>();
		for (String w : XemChatProtection.INSTANCE.getWords().getSection("Profanity").keySet()) {
			words.add(
					new Word(w,
							XemChatProtection.INSTANCE.getWords().getStringList("Profanity." + w + ".aliases"),
							XemChatProtection.INSTANCE.getWords().getStringList("Profanity." + w + ".ignoreWith")));
		}
		return words;
	}

	public static List<String> getWhitelistedLinks() {
		return XemChatProtection.INSTANCE.getWords().getStringList("WhitelistedLinks");
	}

	public static List<String> getLinkContains() {
		return XemChatProtection.INSTANCE.getWords().getStringList("LinkContains");
	}

}
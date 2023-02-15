package me.xemu.xemchatprotection.manager;

import me.xemu.xemchatprotection.XemChatProtection;
import org.apache.commons.lang3.StringUtils;

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

	public static List<String> getProfanityShield() {
		return XemChatProtection.INSTANCE.getWords().getStringList("ProfanityShield");
	}

	public static List<String> getWhitelistedLinks() {
		return XemChatProtection.INSTANCE.getWords().getStringList("WhitelistedLinks");
	}

	public static List<String> getLinkContains() {
		return XemChatProtection.INSTANCE.getWords().getStringList("LinkContains");
	}

}
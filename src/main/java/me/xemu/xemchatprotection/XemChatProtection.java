package me.xemu.xemchatprotection;

import de.leonhard.storage.Json;
import de.leonhard.storage.SimplixBuilder;
import de.leonhard.storage.Yaml;
import de.leonhard.storage.internal.settings.DataType;
import lombok.Getter;
import me.xemu.xemchatprotection.event.MessageSendEvent;
import me.xemu.xemchatprotection.manager.ConfigurationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public final class XemChatProtection extends JavaPlugin {

	public static XemChatProtection INSTANCE;
	public static boolean PROFANITY_SHIELD;
	public static boolean ADVERTISE_SHIELD;

	private Yaml configuration;
	private Yaml words;
	private Json violationData;

	/* Managers */
	private ConfigurationManager configurationManager;

	@Override
	public void onEnable() {
		// Plugin startup logic
		INSTANCE = this;

		if (!getDataFolder().exists()) getDataFolder().mkdir();

		this.configuration = new Yaml("config", "plugins/XemChatProtection");
		this.words = new Yaml("words", "plugins/XemChatProtection");
		this.violationData = new Json("violationData", "plugins/XemChatProtection");

		this.configurationManager = new ConfigurationManager();

		PROFANITY_SHIELD = getConfiguration().getBoolean("Settings.ProfanityShield");
		ADVERTISE_SHIELD = getConfiguration().getBoolean("Settings.AdvertiseShield");

		getServer().getPluginManager().registerEvents(new MessageSendEvent(), this);
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}

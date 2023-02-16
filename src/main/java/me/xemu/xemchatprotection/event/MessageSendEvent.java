package me.xemu.xemchatprotection.event;

import me.xemu.xemchatprotection.builder.AlertBuilder;
import me.xemu.xemchatprotection.builder.IdentifierBuilder;
import me.xemu.xemchatprotection.builder.MessageBuilder;
import me.xemu.xemchatprotection.builder.TimestampBuilder;
import me.xemu.xemchatprotection.reader.MessageReader;
import me.xemu.xemchatprotection.reader.ResponseCode;
import me.xemu.xemchatprotection.utils.ConfigFile;
import me.xemu.xemchatprotection.utils.Configurable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageSendEvent implements Listener {

	@EventHandler
	public void onMessageSend(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		MessageReader reader = new MessageReader(event.getMessage().toLowerCase());
		ResponseCode response = reader.read();

		if (response != ResponseCode.ACCEPTED) {
			event.setCancelled(true);
		}

		switch (response) {
			case DECLINED_PROFANITY:
				new AlertBuilder(player, ResponseCode.DECLINED_PROFANITY, event.getMessage()).execute();
				new MessageBuilder(new Configurable("Messages.Alerts.ProfanityMessage", ConfigFile.CONFIG_YML).string())
						.colors()
						.prefix()
						.send(player);
				break;
			case DECLINED_ADVERTISEMENT:
				new AlertBuilder(player, ResponseCode.DECLINED_ADVERTISEMENT, event.getMessage()).execute();
				new MessageBuilder(new Configurable("Messages.Alerts.LinkMessage", ConfigFile.CONFIG_YML).string())
						.colors()
						.prefix()
						.send(player);
				break;
		}

	}

}

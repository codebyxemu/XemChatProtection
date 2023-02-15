package me.xemu.xemchatprotection.event;

import me.xemu.xemchatprotection.builder.AlertBuilder;
import me.xemu.xemchatprotection.builder.IdentifierBuilder;
import me.xemu.xemchatprotection.builder.MessageBuilder;
import me.xemu.xemchatprotection.builder.TimestampBuilder;
import me.xemu.xemchatprotection.manager.ViolationManager;
import me.xemu.xemchatprotection.reader.MessageReader;
import me.xemu.xemchatprotection.reader.ResponseCode;
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
				new MessageBuilder("<prefix> &cMessage could not be sent. Includes profanity!", true, true)
						.send(player);
				break;
			case DECLINED_ADVERTISEMENT:
				new AlertBuilder(player, ResponseCode.DECLINED_ADVERTISEMENT, event.getMessage()).execute();
				new MessageBuilder("<prefix> &cMessage could not be sent. Includes a non-whitelist link.", true, true)
						.send(player);
				break;
		}

		new ViolationManager().createViolation(IdentifierBuilder.buildIdentifier(4), player, event.getMessage(), response, TimestampBuilder.build(System.currentTimeMillis()));
	}

}

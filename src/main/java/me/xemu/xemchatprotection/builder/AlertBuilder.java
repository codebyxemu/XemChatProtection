package me.xemu.xemchatprotection.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.reader.ResponseCode;
import me.xemu.xemchatprotection.utils.ConfigFile;
import me.xemu.xemchatprotection.utils.Configurable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class AlertBuilder {

	private Player offender;
	private ResponseCode responseCode;
	private String blockedMessage;

	public void execute() {

		List<String> configMessage = new Configurable("Messages.Alerts.AlertMessage", ConfigFile.CONFIG_YML).strings();

/*		String[] message = new String[]{
				"&8&m-------------- &r &c&lBlocked Message&r &8&m--------------",
				"&7User: &e" + offender.getName(),
				"&7Response-Code: &4" + responseCode.name(),
				"&7Blocked Message: &c" + blockedMessage,
				"&8&m-------------- &r &c&lBlocked Message&r &8&m--------------",
		};*/

		Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(XemChatProtection.INSTANCE.getConfiguration().getString("StaffPermission"))).forEach(member -> {
			for (String m : configMessage) {
				//member.sendMessage(ChatColor.translateAlternateColorCodes('&', m));
				new MessageBuilder(m)
						.colors()
						.placeholder("<offender>", offender.getName())
						.placeholder("<responseCode>", responseCode.name())
						.placeholder("<blockedMessage>", blockedMessage)
						.send(member);
			}
		});
	}

}

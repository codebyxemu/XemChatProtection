package me.xemu.xemchatprotection.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.reader.ResponseCode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class AlertBuilder {

	private Player offender;
	private ResponseCode responseCode;
	private String blockedMessage;

	public void execute() {
		Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(XemChatProtection.INSTANCE.getConfiguration().getString("StaffPermission"))).forEach(member -> {
			new MessageBuilder("&8&m------------&r &c&lBlocked Message&r &8&m------------", false, true).send(member);
			new MessageBuilder("&7User: &e[offTestender_name]", false, true).placeholder("[offender_name]", offender.getName()).send(member);
			new MessageBuilder("&7Response-Code: &4[response_code]", false, true).placeholder("[response_code]", responseCode.name()).send(member);
			new MessageBuilder("&7Blocked Message: &c[message]", false, true).placeholder("[message]", blockedMessage).send(member);
			new MessageBuilder("&8&m------------&r &c&lBlocked Message&r &8&m------------", false, true).send(member);
		});
	}

}

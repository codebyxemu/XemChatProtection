package me.xemu.xemchatprotection.violation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.xemu.xemchatprotection.reader.ResponseCode;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Getter
@Setter
public class Violation {
	private String id;
	private Player offender;
	private String message;
	private ResponseCode responseCode;
	private String timeStamp;
}

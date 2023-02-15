package me.xemu.xemchatprotection.manager;

import de.leonhard.storage.Json;
import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.reader.ResponseCode;
import org.bukkit.entity.Player;

import java.sql.Timestamp;

public class ViolationManager {

	private Json data = XemChatProtection.INSTANCE.getViolationData();

	public void createViolation(String id, Player offender, String message, ResponseCode code, String timestamp) {
		data.set(id + ".offender", offender.getUniqueId());
		data.set(id + ".message", message);
		data.set(id + ".code", code.name());
		data.set(id + ".timestamp", timestamp);
		data.write();
	}

	public void deleteViolation(String id) {
		data.remove(id);
	}

}

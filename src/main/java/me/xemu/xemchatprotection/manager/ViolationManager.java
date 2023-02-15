package me.xemu.xemchatprotection.manager;

import de.leonhard.storage.Json;
import me.xemu.xemchatprotection.XemChatProtection;
import me.xemu.xemchatprotection.reader.ResponseCode;
import me.xemu.xemchatprotection.violation.Violation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ViolationManager {

	private Json data = XemChatProtection.INSTANCE.getViolationData();

	private List<Violation> violations;

	public ViolationManager() {
		violations = new ArrayList<>();
		data.keySet().forEach(id -> {
			violations.add(fromId(id));
		});
	}

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

	public Violation fromId(String id) {
		UUID offender = UUID.fromString(data.getString(id + ".offender"));
		String message = data.getString(id + ".message");
		ResponseCode code = ResponseCode.valueOf(data.getString(id + ".code"));
		String timestamp = data.getString(id + ".timestamp");

		return new Violation(
				id,
				Bukkit.getPlayer(offender),
				message,
				code,
				timestamp
		);
	}

	public List<Violation> filter(Predicate<Violation> predicate) {
		return violations.stream().filter(predicate).collect(Collectors.toList());
	}

}

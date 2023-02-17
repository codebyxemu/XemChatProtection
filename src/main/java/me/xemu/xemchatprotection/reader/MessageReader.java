package me.xemu.xemchatprotection.reader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.xemu.xemchatprotection.manager.ConfigurationManager;
import me.xemu.xemchatprotection.utils.MessageUtils;
import me.xemu.xemchatprotection.utils.Word;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MessageReader {

	private String readMessage;

	public ResponseCode read() {
		for (Word word : ConfigurationManager.getWords()) {
			String lowerCaseReadMessage = readMessage.toLowerCase();
			String lowerCaseWord = word.getWord().toLowerCase();

			if (lowerCaseReadMessage.contains(lowerCaseWord)) {
				if (word.getIgnoreWith() != null) {
					for (String ignore : word.getIgnoreWith()) {
						if (lowerCaseWord.contains(ignore.toLowerCase()) || lowerCaseReadMessage.contains(ignore.toLowerCase())) {
							return ResponseCode.ACCEPTED;
						}
					}
				}

				if (word.getAliases() != null) {
					for (String alias : word.getAliases()) {
						if (lowerCaseReadMessage.contains(alias.toLowerCase())) {
							if (word.getIgnoreWith() != null) {
								for (String ignore : word.getIgnoreWith()) {
									if (alias.toLowerCase().contains(ignore.toLowerCase())) {
										return ResponseCode.ACCEPTED;
									}
								}
							}
							return ResponseCode.DECLINED_PROFANITY;
						}
					}
				}

				return ResponseCode.DECLINED_PROFANITY;
			}
		}

		for (String identifier : ConfigurationManager.getLinkContains()) {
			if (readMessage.toLowerCase().contains(identifier.toLowerCase())) {
				return ResponseCode.DECLINED_ADVERTISEMENT;
			}
		}

		return ResponseCode.ACCEPTED;
	}


}

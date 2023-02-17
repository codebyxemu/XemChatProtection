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
	/*	for (String profanityWord : ConfigurationManager.getProfanityShield()) {
			if (readMessage.toLowerCase().contains(profanityWord.toLowerCase())) {
				return ResponseCode.DECLINED_PROFANITY;
			}
		}*/

		for (Word word : ConfigurationManager.getWords()) {
			if (word.getWord().toLowerCase().contains(readMessage)) {
				if (word.getIgnoreWith() != null) {
					for (String ignore : word.getIgnoreWith()) {
						if (readMessage.toLowerCase().contains(readMessage.toLowerCase()) || word.getWord().toLowerCase().contains(ignore.toLowerCase())) {
							return ResponseCode.ACCEPTED;
						}
					}
				}

				if (word.getAliases() != null) {
					for (String alias : word.getAliases()) {
						if (readMessage.toLowerCase().contains(alias.toLowerCase())) {
							if (word.getIgnoreWith() != null) {
								for (String ignore : word.getIgnoreWith()) {
									if (alias.toLowerCase().contains(ignore)) {
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

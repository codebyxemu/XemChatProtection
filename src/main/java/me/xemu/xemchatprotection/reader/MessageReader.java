package me.xemu.xemchatprotection.reader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.xemu.xemchatprotection.manager.ConfigurationManager;
import me.xemu.xemchatprotection.word.Word;

import javax.security.auth.login.Configuration;

@AllArgsConstructor
@Getter
@Setter
public class MessageReader {

	private String readMessage;

	public ResponseCode read() {
		/*for (String profanityWord : ConfigurationManager.getProfanityShield()) {
			if (readMessage.toLowerCase().contains(profanityWord.toLowerCase())) {
				return ResponseCode.DECLINED_PROFANITY;
			}
		}*/

		for (Word word : ConfigurationManager.getWords()) {

		}

		for (String identifier : ConfigurationManager.getLinkContains()) {
			if (readMessage.toLowerCase().contains(identifier.toLowerCase())) {
				return ResponseCode.DECLINED_ADVERTISEMENT;
			}
		}

		return ResponseCode.ACCEPTED;
	}

}

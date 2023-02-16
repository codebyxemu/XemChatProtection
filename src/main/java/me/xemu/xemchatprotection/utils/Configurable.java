package me.xemu.xemchatprotection.utils;

import me.xemu.xemchatprotection.XemChatProtection;

import java.util.List;

enum ConfigFile {
	CONFIG_YML,
	WORDS_YML,
	DATA_JSON
}

public class Configurable {

	private String path;
	private ConfigFile type;
	private Object returned;

	// TODO: Java Docs
	public Configurable(String path, ConfigFile type) {
		switch (type) {
			case CONFIG_YML:
				returned = XemChatProtection.INSTANCE.getConfiguration().get(path);
				break;
			case WORDS_YML:
				returned = XemChatProtection.INSTANCE.getWords().get(path);
				break;
			case DATA_JSON:
				returned = XemChatProtection.INSTANCE.getViolationData().get(path);
				break;
		}
	}

	public String string() {
		return (String) returned;
	}

	public int integer() {
		return (int) returned;
	}

	public boolean bool() {
		return (boolean) returned;
	}

	public List<Object> objects() {
		return (List<Object>) returned;
	}

	public List<String> strings() {
		return (List<String>) returned;
	}

}
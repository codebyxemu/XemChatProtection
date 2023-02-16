package me.xemu.xemchatprotection.utils;

import me.xemu.xemchatprotection.XemChatProtection;

import java.util.List;

/**
 * This class is responsible for the plugin operation in getting
 * configuration paths
 */
public class Configurable {

	private String path;
	private ConfigFile type;
	private Object returned;

	/**
	 * Initiate the required values.
	 * @param path The path to get from the specified "type" parameter.
	 * @param type The configuration type to get it from. Located at: ${@link ConfigFile}
	 */
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

	/**
	 * Get path as string
	 * @return String return value
	 */
	public String string() {
		return (String) returned;
	}

	/**
	 * Get path as integer
	 * @return Integer return value
	 */
	public int integer() {
		return (int) returned;
	}

	/**
	 * Get path as boolean
	 * @return Boolean return value
	 */
	public boolean bool() {
		return (boolean) returned;
	}

	/**
	 * Get object as List
	 * @return List return value
	 */
	public List<Object> objects() {
		return (List<Object>) returned;
	}

	/**
	 * Get path as StringList
	 * @return StringList return value
	 */
	public List<String> strings() {
		return (List<String>) returned;
	}

}
package me.xemu.xemchatprotection.builder;

import org.apache.commons.lang3.RandomStringUtils;

public class IdentifierBuilder {
	public static String buildIdentifier(int length) {
		return RandomStringUtils.random(length);
	}
}

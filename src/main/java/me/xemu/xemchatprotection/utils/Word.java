package me.xemu.xemchatprotection.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Word {
	private String word;
	private List<String> aliases;
	private List<String> ignoreWith;
}

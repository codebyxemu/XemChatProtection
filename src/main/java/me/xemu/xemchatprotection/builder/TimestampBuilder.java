package me.xemu.xemchatprotection.builder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampBuilder {

	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String build(long time) {
		Timestamp timestamp = new Timestamp(time);
		return sdf3.format(timestamp);
	}

}

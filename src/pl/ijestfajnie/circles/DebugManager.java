package pl.ijestfajnie.circles;

import java.text.MessageFormat;

public class DebugManager {
	public static void debug(int code) {
		System.out.println(MessageFormat.format("{0}", code));
	}
}

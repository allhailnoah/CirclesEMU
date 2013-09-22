/**
 * 
 */
package pl.ijestfajnie.circles;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author michcioperz
 *
 */
public class CirclesEmulator {
	
	private String[] cartridge = null;

	/**
	 * 
	 */
	public CirclesEmulator(FileInputStream file) {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(file));
		List<String> strr = new ArrayList<String>();
		String line = "";
		try {
			while ((line = bfr.readLine()) != null) {
				strr.add(line);
			}
		} catch (IOException e) {
			DebugManager.debug(5);
		}
		cartridge = new String[strr.size()];
		for (int i = 0; i < strr.size(); i++) {
			cartridge[i] = strr.get(i);
		}
		new CirclesUnit(cartridge);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length > 0) {
				new CirclesEmulator(new FileInputStream(args[0]));
			} else {
				new CirclesEmulator(new FileInputStream("cartri.dge"));
			}
		} catch (FileNotFoundException fnf) {
			DebugManager.debug(3);
		}
	}

}

/**
 * 
 */
package pl.ijestfajnie.circles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * @author michcioperz
 *
 */
public class CirclesEmulator {
	
	private String[] cartridge = null;

	/**
	 * 
	 */
	public CirclesEmulator(File file) {
		try {
			cartridge  = (String[]) Files.readAllLines(file.toPath(), Charset.defaultCharset()).toArray();
		} catch (IOException e) {
			System.exit(5);
		}
		new CirclesUnit(cartridge);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			new CirclesEmulator(new File(args[0]));
		} else {
			new CirclesEmulator(new File("cartri.dge"));
		}
	}

}

package pl.ijestfajnie.circles;

public class CirclesUnit {
	
	private int currentPointer = 0;
	private final String[] cartridge;
	
	private byte[] memory;
	private byte[] screenmemory;

	public CirclesUnit(String[] cartridge) {
		memory = new byte[16];
		for (byte bt: memory) {
			bt = 0;
		}
		screenmemory = new byte[16];
		for (byte bt: screenmemory) {
			bt = 0;
		}
		this.cartridge = cartridge;
		for (; currentPointer < cartridge.length; currentPointer++) {
			parse();
		}
	}
	
	public void parse() {
		switch (cartridge[currentPointer]) {
			case CircleCommands.CMD_ADD:
				int toadd = Integer.parseInt("0b" + cartridge[currentPointer++]);
				int pointer1 = Integer.parseInt("0b" + currentPointer);
				memory[pointer1] += toadd;
				break;
			case CircleCommands.CMD_SUB:
				int tosub = Integer.parseInt("0b" + cartridge[currentPointer++]);
				int pointer2 = Integer.parseInt("0b" + currentPointer);
				memory[pointer2] -= tosub;
				break;
			case CircleCommands.CMD_SET:
				int toset = Integer.parseInt("0b" + cartridge[currentPointer++]);
				int pointer3 = Integer.parseInt("0b" + currentPointer);
				memory[pointer3] = (byte) toset;
				break;
			default:
				System.exit(4);
				break;
		}
	}
}

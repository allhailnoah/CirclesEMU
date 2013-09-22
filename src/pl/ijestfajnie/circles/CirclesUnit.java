package pl.ijestfajnie.circles;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class CirclesUnit extends JFrame {
	
	private int currentPointer = 0;
	private final String[] cartridge;
	
	private byte[] memory;
	private byte[] screenmemory;

	public CirclesUnit(String[] cartridge) {
		memory = new byte[64];
		for (int i = 0; i < memory.length; i++) {
			memory[i] = 0;
		}
		screenmemory = new byte[64];
		for (int i = 0; i < screenmemory.length; i++) {
			screenmemory[i] = 0;
		}
		this.cartridge = cartridge;
		for (; currentPointer < cartridge.length; currentPointer++) {
			parse();
		}
	}
	
	public void parse() {
		System.out.println("Parsing");
		switch (cartridge[currentPointer]) {
			case CircleCommands.CMD_ADD:
				int toadd = Integer.parseInt(cartridge[++currentPointer], 2);
				int pointer1 = Integer.parseInt(cartridge[++currentPointer], 2);
				memory[pointer1] += toadd;
				break;
			case CircleCommands.CMD_SUB:
				int tosub = Integer.parseInt(cartridge[++currentPointer], 2);
				int pointer2 = Integer.parseInt(cartridge[++currentPointer], 2);
				memory[pointer2] -= tosub;
				break;
			case CircleCommands.CMD_SET:
				int toset = Integer.parseInt(cartridge[++currentPointer], 2);
				int pointer3 = Integer.parseInt(cartridge[++currentPointer], 2);
				memory[pointer3] = (byte) toset;
				break;
			case CircleCommands.CMD_SAD:
				int tosad = Integer.parseInt(cartridge[++currentPointer], 2);
				int pointer4 = Integer.parseInt(cartridge[++currentPointer], 2);
				screenmemory[pointer4] += tosad;
				break;
			case CircleCommands.CMD_SSU:
				int tossu = Integer.parseInt(cartridge[++currentPointer], 2);
				int pointer5 = Integer.parseInt(cartridge[++currentPointer], 2);
				screenmemory[pointer5] -= tossu;
				break;
			case CircleCommands.CMD_SST:
				int tosst = Integer.parseInt(cartridge[++currentPointer], 2);
				int pointer6 = Integer.parseInt(cartridge[++currentPointer], 2);
				screenmemory[pointer6] = (byte) tosst;
				break;
			case CircleCommands.CMD_FWD:
				int tofwd = Integer.parseInt(cartridge[++currentPointer], 2);
				currentPointer += tofwd;
				break;
			case CircleCommands.CMD_BCK:
				int tobck = Integer.parseInt(cartridge[++currentPointer], 2);
				currentPointer -= tobck;
				break;
			case CircleCommands.CMD_CHK:
				int chktype = Integer.parseInt(cartridge[++currentPointer], 2);
				int onecell = Integer.parseInt(cartridge[++currentPointer], 2);
				int onevalu = memory[onecell];
				int twocell = Integer.parseInt(cartridge[++currentPointer], 2);
				int twovalu = memory[twocell];
				if (chktype == Integer.parseInt(CircleCommands.ConditionType.CND_EQL, 2)) {
					if (onevalu == twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt(cartridge[++currentPointer], 2);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt(CircleCommands.ConditionType.CND_MOR, 2)) {
					if (onevalu > twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt(cartridge[++currentPointer], 2);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt(CircleCommands.ConditionType.CND_LES, 2)) {
					if (onevalu < twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt(cartridge[++currentPointer], 2);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt(CircleCommands.ConditionType.CND_MOE, 2)) {
					if (onevalu >= twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt(cartridge[++currentPointer], 2);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt(CircleCommands.ConditionType.CND_LOE, 2)) {
					if (onevalu <= twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt(cartridge[++currentPointer], 2);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt(CircleCommands.ConditionType.CND_NOT, 2)) {
					if (onevalu != twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt(cartridge[++currentPointer], 2);
						currentPointer += toskip;
					}
				}
				break;
			default:
				DebugManager.debug(4);
				break;
		}
	}
}

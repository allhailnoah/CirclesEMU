package pl.ijestfajnie.circles;

import javax.swing.JFrame;

public class CirclesUnit extends JFrame {
	
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
				int toadd = Integer.parseInt("0b" + cartridge[++currentPointer]);
				int pointer1 = Integer.parseInt("0b" + ++currentPointer);
				memory[pointer1] += toadd;
				break;
			case CircleCommands.CMD_SUB:
				int tosub = Integer.parseInt("0b" + cartridge[++currentPointer]);
				int pointer2 = Integer.parseInt("0b" + ++currentPointer);
				memory[pointer2] -= tosub;
				break;
			case CircleCommands.CMD_SET:
				int toset = Integer.parseInt("0b" + cartridge[++currentPointer]);
				int pointer3 = Integer.parseInt("0b" + ++currentPointer);
				memory[pointer3] = (byte) toset;
				break;
			case CircleCommands.CMD_SAD:
				int tosad = Integer.parseInt("0b" + cartridge[++currentPointer]);
				int pointer4 = Integer.parseInt("0b" + ++currentPointer);
				memory[pointer4] += tosad;
				break;
			case CircleCommands.CMD_SSU:
				int tossu = Integer.parseInt("0b" + cartridge[++currentPointer]);
				int pointer5 = Integer.parseInt("0b" + ++currentPointer);
				memory[pointer5] -= tossu;
				break;
			case CircleCommands.CMD_SST:
				int tosst = Integer.parseInt("0b" + cartridge[++currentPointer]);
				int pointer6 = Integer.parseInt("0b" + ++currentPointer);
				memory[pointer6] = (byte) tosst;
				break;
			case CircleCommands.CMD_FWD:
				int tofwd = Integer.parseInt("0b" + cartridge[++currentPointer]);
				currentPointer += tofwd;
				break;
			case CircleCommands.CMD_BCK:
				int tobck = Integer.parseInt("0b" + cartridge[++currentPointer]);
				currentPointer -= tobck;
				break;
			case CircleCommands.CMD_CHK:
				int chktype = Integer.parseInt("0b" + cartridge[++currentPointer]);
				int onecell = Integer.parseInt("0b" + cartridge[++currentPointer]);
				int onevalu = memory[onecell];
				int twocell = Integer.parseInt("0b" + cartridge[++currentPointer]);
				int twovalu = memory[twocell];
				if (chktype == Integer.parseInt("0b" + CircleCommands.ConditionType.CND_EQL)) {
					if (onevalu == twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt("0b" + cartridge[++currentPointer]);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt("0b" + CircleCommands.ConditionType.CND_MOR)) {
					if (onevalu > twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt("0b" + cartridge[++currentPointer]);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt("0b" + CircleCommands.ConditionType.CND_LES)) {
					if (onevalu < twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt("0b" + cartridge[++currentPointer]);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt("0b" + CircleCommands.ConditionType.CND_MOE)) {
					if (onevalu >= twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt("0b" + cartridge[++currentPointer]);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt("0b" + CircleCommands.ConditionType.CND_LOE)) {
					if (onevalu <= twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt("0b" + cartridge[++currentPointer]);
						currentPointer += toskip;
					}
				} else if (chktype == Integer.parseInt("0b" + CircleCommands.ConditionType.CND_NOT)) {
					if (onevalu != twovalu) {
						currentPointer++;
					} else {
						int toskip = Integer.parseInt("0b" + cartridge[++currentPointer]);
						currentPointer += toskip;
					}
				}
				break;
			default:
				System.exit(4);
				break;
		}
	}
}

package pl.ijestfajnie.circles;

public final class CircleCommands {
	public final static String CMD_CHK = "00001"; //checks for condition
	public final static String CMD_FWD = "00010"; //skips next few fields
	public final static String CMD_BCK = "00011"; //goes back few fields
	public final static String CMD_SET = "00100"; //assigns a field a value
	public final static String CMD_ADD = "00101"; //adds a value to field's value
	public final static String CMD_SUB = "00110"; //subtracts a value from field's value
	//public final static String CMD_RDR = "00111"; //pushes to the render
	public final static String CMD_SST = "01000"; //sets screen memory field
	public final static String CMD_SAD = "01001"; //add to screen memory
	public final static String CMD_SSU = "01010"; //subtract from screen memory
	public final class ConditionType {
		public final static String CND_EQL = "00001"; //equals
		public final static String CND_MOR = "00010"; //more than
		public final static String CND_LES = "00011"; //less than
		public final static String CND_MOE = "00100"; //more or equals
		public final static String CND_LOE = "00101"; //less or equals
		public final static String CND_NOT = "00110"; //not equals
	}
}

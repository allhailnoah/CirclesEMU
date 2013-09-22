package pl.ijestfajnie.circles.emulator;

public class RandomAccessMemory {
	
	//Quite small, but results in compatibility with older models
	public int MAX_BYTES = 64;
	public int[] ram = new int[MAX_BYTES];
	public int[] page_owner = new int[MAX_BYTES];
	public int free_pages = MAX_BYTES;
		
	public int flags_register = Integer.parseInt("00000000", 2);
		
	//Start the GPU
	public RandomAccessMemory() {
		for (int i = 0; i < ram.length; i++) {
			ram[i] = 0;
		}
		for (int i = 0; i < page_owner.length; i++) {
			page_owner[i] = 0;
		}
	}

}
